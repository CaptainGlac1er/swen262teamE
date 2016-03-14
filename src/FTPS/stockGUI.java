package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class StockGUI extends JFrame {
    JFrame frame = this;
    JPanel infoPanel;
    StockBack backend;
    public StockGUI(StockBack back){
        backend = back;
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel, BorderLayout.NORTH);

        this.setSize(650,200);
        setVisible(true);
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(infoPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton addStock = new JButton("Buy new stock");
        addStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockChild stock = null;
                addStockRow(stock);
                    new SearchStockGUI(new SearchStock(FTPS.AllStocks),backend);
            }
        });
        frame.add(addStock, BorderLayout.SOUTH);
    }
    public void clear(){
        infoPanel.removeAll();
        infoPanel.revalidate();
        infoPanel.repaint();
    }
    public void addStockRow(StockChild stock){
        JPanel row = new JPanel(new GridLayout(1,5));

        row.setPreferredSize(new Dimension(600,60));
        JLabel ticker = new JLabel("");
        JLabel price = new JLabel("");
        if(stock != null){
            ticker.setText(stock.getStockAbbr());
            price.setText(stock.getWorth() + "x" + stock.getCount() + "=" + stock.getTotWorth());
            stock.addObserver(price);
        }
        JTextField buySell = new JTextField();
        JButton buy = new JButton("Buy");
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuyStock newOrder = new BuyStock(backend.portfolio.getAssets(),stock, Integer.parseInt(buySell.getText()));
                newOrder.execute();
            }
        });
        JButton sell = new JButton("Sell");
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveStock newOrder = new RemoveStock(backend.portfolio.getAssets(),stock, Integer.parseInt(buySell.getText()));
                newOrder.execute();

            }
        });




        row.add(ticker);
        row.add(price);
        row.add(buySell);
        row.add(buy);
        row.add(sell);
        infoPanel.add(row);
        frame.revalidate();
        frame.repaint();
    }
}
