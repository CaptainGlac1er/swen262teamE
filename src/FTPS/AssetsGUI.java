package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by CaptainGlac1er on 3/26/2016.
 */
public class AssetsGUI extends PageGUI implements Updatable{
    private AssetsBackend assetsBackend;
    private AssetsUpdater assetsUpdater;
    Portfolio portfolio;
    public AssetsGUI(AssetsBackend assetsBackend, AssetsUpdater assetsUpdater, Portfolio portfolio){
        this.portfolio = portfolio;
        this.assetsBackend = assetsBackend;
        this.assetsUpdater = assetsUpdater;
        addComponents();
    }
    @Override
    public void addComponents() {
        panel.setBackground(Color.darkGray);
        panel.setLayout(new BorderLayout());
        JPanel data = new JPanel();
        data.setLayout(new BoxLayout(data,BoxLayout.Y_AXIS));
        ScrollPane pane = new ScrollPane();
        pane.add(data);
        for(StockChild c : assetsBackend.getStocks()) {
            data.add(getAssetRow(c.getStockAbbr(), c.getWorth() +"", c.getTotWorth() + "", c));
        }
        panel.add(pane);
        JPanel bottom = new JPanel();
        JButton addStock = new JButton("Buy Stock");
        addStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchStockGUI(new SearchStock(), assetsBackend, assetsBackend.getPortfolio());
                panel.removeAll();
                addComponents();
                panel.revalidate();
                panel.repaint();

            }
        });
        bottom.add(addStock);
        panel.add(bottom, BorderLayout.SOUTH);

    }
    public JPanel getAssetRow(String left, String middle, String right, StockChild info){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,20));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    new StockGUI(info,portfolio);
                }
            }
        });

        JLabel ticker = new JLabel(left);
        JLabel price = new JLabel(middle);
        JLabel totalValue = new JLabel(right);
        System.out.println(ticker + " " + "added to Jpanel");

        panel.add(ticker);
        panel.add(price);
        panel.add(totalValue);
        return panel;
    }
    public void clear(){
        panel.removeAll();
    }

    @Override
    public void update() {
        clear();
        addComponents();
        panel.revalidate();
        panel.repaint();
    }
}
