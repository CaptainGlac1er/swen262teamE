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
public class portfolioGUI extends JFrame {
    User user;
    portfolio port;
    public portfolioGUI(User user, portfolio port){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.port = port;
        this.user = user;
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel, BorderLayout.NORTH);

        JPanel tiles = new JPanel(new GridLayout(2,2));
        JButton cash = new JButton("Cash");
        cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<cashAccount> tests = port.getAssets().GetAccounts();
                new cashGUI(tests);
            }
        });
        tiles.add(cash);
        JButton stocks = new JButton("Stocks");
        tiles.add(stocks);
        JButton trans = new JButton("Transactions");
        tiles.add(trans);
        JButton pred = new JButton("Predictions");
        tiles.add(pred);
        this.add(tiles, BorderLayout.CENTER);

        this.setSize(300,400);
        setVisible(true);
    }

}
