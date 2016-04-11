package FTPS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public class PortfolioBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    private double portfolioWorth = 0;
    AssetsBackend assetsBackend;
    AccountBackend accountBackend;
    WatchListBackend watchListBackend;
    TransactionsBackend transactionsBackend;
    PortfolioGUI portfolioGUI;
    Portfolio portfolio;

    /**
     *
     * @param pageUpdater
     * @param user
     */
    public PortfolioBackend(PageUpdater pageUpdater, User user){
        portfolio = new Portfolio(user, this);
        askRefresh();
        assetsBackend = new AssetsBackend(new PageUpdater(), portfolio);
        accountBackend = new AccountBackend(new PageUpdater(), portfolio);
        watchListBackend = new WatchListBackend(new PageUpdater(), portfolio);
        transactionsBackend = new TransactionsBackend(new PageUpdater(), portfolio);
        portfolioGUI = new PortfolioGUI(this, new PortfolioUpdater(), (AccountGUI)accountBackend.getPage(), (AssetsGUI)assetsBackend.getPage(), (WatchListGUI)watchListBackend.getPage(), (TransactionsGUI) transactionsBackend.getPage());
        setGUI(portfolioGUI);
        this.pageUpdater = pageUpdater;
        pageUpdater.setCurrentPageAndBackend(getGUI(), this);
    }
    public Portfolio getPortfolio(){
        return portfolio;
    }
    @Override
    public void update() {
        assetsBackend.update();
        accountBackend.update();
        watchListBackend.update();
        transactionsBackend.update();
        portfolioGUI.update();
    }
    public void update(double currentPortfolioWorth){
        portfolioWorth = currentPortfolioWorth;
        update();
    }
    public void askRefresh(){
        JFrame askRefresh = new JFrame();
        askRefresh.setSize(300,150);
        JPanel refresh = new JPanel();
        JTextField refreshRate = new JTextField();
        refreshRate.setColumns(10);
        refresh.add(refreshRate);
        JButton refreshUpdateButton = new JButton("Update Refresh");
        refreshUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int refresh = Integer.parseInt(refreshRate.getText());
                if(portfolio.getWatchList().beginTimer(refresh))
                    askRefresh.dispose();
                else
                    JOptionPane.showMessageDialog(null, "Input a value larger than 30");

            }
        });
        refresh.add(refreshUpdateButton);
        askRefresh.add(refresh);
        askRefresh.setVisible(true);
    }
    public void savePortfolio(){
        portfolio.exportPortfolio(false);
    }
}
