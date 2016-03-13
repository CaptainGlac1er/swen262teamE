package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class PortfolioGUI extends JFrame {
    User user;
    Portfolio port;
    public PortfolioGUI(User user, Portfolio port){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.port = port;
        this.user = user;
        JLabel testLabel = new JLabel(user.getUsername());
        this.add(testLabel, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(2,2));
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
                new StockBack(null);
            }
        });
        tiles.add(stocks);
        JButton trans = new JButton("Transactions");
        tiles.add(trans);
        JButton pred = new JButton("Predictions");
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
        this.add(settings, BorderLayout.NORTH);
        this.setSize(300,400);
        setVisible(true);
    }

}
