package FTPS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class StockGUI extends JFrame {
    JFrame frame = this;
    JPanel infoPanel;
    public StockGUI(){
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel, BorderLayout.NORTH);

        this.setSize(300,400);
        setVisible(true);
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        frame.add(infoPanel, BorderLayout.CENTER);
    }
    public void addStockRow(StockChild stock){
        JPanel row = new JPanel(new GridLayout(1,5));
        JLabel ticker = new JLabel(stock.getStockAbbr());
        JLabel price = new JLabel(stock.getWorth() + "x" + stock.getCount() + "=" + stock.getTotWorth());
        JTextField buySell = new JTextField();
        JButton buy = new JButton("Buy");
        JButton sell = new JButton("Sell");




        row.add(ticker);
        row.add(price);
        row.add(buySell);
        row.add(buy);
        row.add(sell);
        infoPanel.add(row);
    }
}
