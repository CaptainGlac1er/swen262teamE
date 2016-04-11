package FTPS;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

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

    /**
     *
     *
     * @param backend backend to handle the logic
     * @param updater updater to update this panel
     * @param accountGUI accountgui panel
     * @param assetsGUI assestgui panel
     * @param watchListGUI watchlistgui panel
     * @param transactionsGUI transactionsgui panel
     */
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
        JPanel headerPanel = new JPanel();

        //setting panel frame
        JButton settings = new JButton("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame settings = new JFrame();
                settings.setSize(300,150);
                JPanel settingsPanel = new JPanel();
                JButton exportButton = new JButton("Export");
                exportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        portfolioBackend.getPortfolio().exportPortfolio(true);
                    }
                });
                settingsPanel.add(exportButton);

                JButton importButton = new JButton("Import");
                importButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        portfolioBackend.getPortfolio().importPortfolio();
                        portfolioBackend.update();
                    }
                });
                settingsPanel.add(importButton);

                JPanel refresh = new JPanel();
                JTextField refreshRate = new JTextField();
                refreshRate.setColumns(10);
                refresh.add(refreshRate);
                JButton refreshUpdateButton = new JButton("Update Refresh");
                refreshUpdateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int refresh = Integer.parseInt(refreshRate.getText());
                        portfolioBackend.getPortfolio().getWatchList().beginTimer(refresh);
                    }
                });
                refresh.add(refreshUpdateButton);
                settingsPanel.add(refresh);
                settings.add(settingsPanel);
                settings.setVisible(true);
            }
        });
        headerPanel.add(settings);

        JButton redo = new JButton("redo");
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolioBackend.getPortfolio().redo();
            }
        });
        headerPanel.add(redo);

        JButton undo = new JButton("Undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolioBackend.getPortfolio().undo();
            }
        });
        headerPanel.add(undo);


        JPanel accountPanel = accountGUI.getPage();
        accountPanel.setPreferredSize(new Dimension(400, 300));
        centerPanel.add(accountPanel);

        JPanel transactionPanel = transactionsGUI.getPage();
        transactionPanel.setPreferredSize(new Dimension(400, 300));
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
        DecimalFormat cash = new DecimalFormat("$###,###.###");
        accountWorth = new JLabel("   Total Value: " + cash.format(portfolioBackend.getPortfolio().totalValue()));
        bottomPanel.add(accountWorth, BorderLayout.EAST);

        panel.add(headerPanel, BorderLayout.NORTH);
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
