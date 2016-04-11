package FTPS;

import javax.swing.*;
import javax.swing.text.html.ObjectView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by CaptainGlac1er on 4/3/2016.
 */
public class StockGUI extends JFrame implements Updatable {
    JLabel owned = new JLabel();
    JLabel price = new JLabel();
    StockChild thisStock;
    public StockGUI(StockChild info, Portfolio portfolio){
        thisStock = info;
        this.setTitle(info.getStockName());
        JFrame stockFrame = this;
        stockFrame.setPreferredSize(new Dimension(300,300));
        JPanel panel = new JPanel();
        JLabel ticker = new JLabel(info.getStockAbbr());
        ticker.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(ticker, BorderLayout.NORTH);

        DecimalFormat cash = new DecimalFormat("$###,###.###");
        price.setText(cash.format(info.getWorth()));
        panel.add(price);

        owned.setText(info.getCount() + "");
        panel.add(owned);

        JPanel bottom = new JPanel();
        JTextField buysell = new JTextField();
        buysell.setColumns(10);
        bottom.add(buysell);
        JButton buyButton = new JButton("Buy");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(buysell.getText());
                portfolio.buyStock(info, amount);
            }
        });
        JButton sellButton = new JButton("Sell");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(buysell.getText());
                portfolio.sellStock(info, amount);

            }
        });
        bottom.add(buyButton);
        bottom.add(sellButton);
        panel.add(bottom, BorderLayout.SOUTH);
        stockFrame.add(panel);
        stockFrame.pack();
        stockFrame.setVisible(true);
    }

    @Override
    public void update() {
        owned.setText(thisStock.getCount() + "");
        price.setText(thisStock.getWorth() +"");
    }
}
