package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public class PortfolioGUI extends PageGUI implements Updatable{
    PortfolioBackend portfolioBackend;
    PortfolioUpdater portfolioUpdater;
    WatchListGUI watchListGUI;
    AccountGUI accountGUI;
    AssetsGUI assetsGUI;
    TransactionsGUI transactionsGUI;
    JLabel accountWorth;
    public PortfolioGUI(PortfolioBackend backend, PortfolioUpdater updater, AccountGUI accountGUI, AssetsGUI assetsGUI, WatchListGUI watchListGUI, TransactionsGUI transactionsGUI){
        portfolioBackend = backend;
        portfolioUpdater = updater;
        this.assetsGUI = assetsGUI;
        this.accountGUI = accountGUI;
        this.watchListGUI = watchListGUI;
        this.transactionsGUI = transactionsGUI;
        pageTitle = "Portfolio";
        portfolioUpdater.register(portfolioBackend);
        addComponents();
    }
    @Override
    public void addComponents() {
        panel.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(400, 600));
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        JPanel accountPanel = accountGUI.getPage();
        accountPanel.setPreferredSize(new Dimension(400, 300));
        centerPanel.add(accountPanel);

        JPanel transactionPanel = transactionsGUI.getPage();
        transactionPanel.setPreferredSize(new Dimension(400, 300));
        transactionPanel.setBackground(Color.blue);
        centerPanel.add(transactionPanel);

        JPanel watchList = watchListGUI.getPage();
        watchList.setPreferredSize(new Dimension(300, 600));
        leftPanel.add(watchList);

        JPanel assetsPanel = assetsGUI.getPage();
        assetsPanel.setPreferredSize(new Dimension(300, 600));
        rightPanel.add(assetsPanel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton saveButton = new JButton("Save Portfolio");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolioBackend.savePortfolio();
            }
        });
        bottomPanel.add(saveButton);
        accountWorth = new JLabel("   Total Value: $" + portfolioBackend.getPortfolio().totalValue());
        bottomPanel.add(accountWorth, BorderLayout.EAST);


        panel.add(leftPanel, BorderLayout.LINE_START);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.LINE_END);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void update() {
        for(Component child: panel.getComponents()) {
            child.revalidate();
            child.repaint();
        }
        accountWorth.setText("   Total Value: $" + portfolioBackend.getPortfolio().totalValue());
        panel.revalidate();
        panel.repaint();
    }
}
