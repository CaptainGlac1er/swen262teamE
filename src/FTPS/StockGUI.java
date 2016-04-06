package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CaptainGlac1er on 4/3/2016.
 */
public class StockGUI extends JFrame {
    public StockGUI(StockChild info, Portfolio portfolio){
        this.setTitle(info.getStockName());
        JFrame stockFrame = this;
        stockFrame.setPreferredSize(new Dimension(300,300));
        JPanel panel = new JPanel();
        JLabel ticker = new JLabel(info.getStockAbbr());
        ticker.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(ticker, BorderLayout.NORTH);
        JLabel price = new JLabel(info.getWorth() +"");
        panel.add(price);

        JLabel owned = new JLabel(info.getCount() + "");
        panel.add(owned);

        JPanel bottom = new JPanel();
        JTextField buysell = new JTextField();
        buysell.setColumns(20);
        bottom.add(buysell);
        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton sellButton = new JButton("Sell");
        bottom.add(buyButton);
        bottom.add(sellButton);
        panel.add(bottom, BorderLayout.SOUTH);
        stockFrame.add(panel);
        stockFrame.pack();
        stockFrame.setVisible(true);
    }
}
