package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class PortfolioGUI extends JFrame {
    User user;
    Portfolio port;

    /**
     * @param user
     * @param port
     */
    public PortfolioGUI(User user, Portfolio port) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.port = port;
        this.user = user;
        makeUI();
        this.setSize(300, 400);
        setVisible(true);
    }

    /**
     * Makes the UI for the PortfolioGUI
     */
    public void makeUI() {
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        JLabel testLabel = new JLabel(user.getUsername());
        topPanel.add(testLabel);

        JPanel tiles = new JPanel(new GridLayout(2, 2));
        JButton cash = new JButton("Cash");
        cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<CashAccount> tests = port.getAssets().GetAccounts();
                new CashGUI(tests);
            }
        });
        tiles.add(cash);
        JButton stocks = new JButton("Stocks");
        stocks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (StockChild s : port.getAssets().GetStocks())
                    System.out.println(s.getStockAbbr() + " " + s.getWorth());
                new StockBack(port);
            }
        });
        tiles.add(stocks);
        JButton trans = new JButton("Transactions");
        trans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransactionGUI(port);
            }
        });
        tiles.add(trans);
        JButton pred = new JButton("Predictions");
        pred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PredictionsGUI(new PortEngine(), port);
            }
        });
        tiles.add(pred);
        this.add(tiles, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save Portfolio");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port.exportPortfolio(false);
            }
        });
        this.add(saveButton, BorderLayout.SOUTH);
        JButton settings = new JButton("Settings");
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingGUI(port);
            }
        });
        topPanel.add(settings);
        this.add(topPanel, BorderLayout.NORTH);
    }

}
