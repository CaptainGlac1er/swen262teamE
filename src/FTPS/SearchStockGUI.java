package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/13/2016.
 */
public class SearchStockGUI extends JFrame {
    SearchStock searchStock;
    JFrame frame = this;
    JPanel results;
    PageBackend output;
    Portfolio portfolio;

    /**
     * @param searchStock
     * @param output
     */
    public SearchStockGUI(SearchStock searchStock, PageBackend output, Portfolio portfolio) {
        this.portfolio = portfolio;
        this.output = output;
        this.searchStock = searchStock;
        addSearchBar();
        results = new JPanel();
        results.setLayout(new BoxLayout(results, BoxLayout.Y_AXIS));
        JScrollPane scrollBar = new JScrollPane(results);
        frame.add(scrollBar);
        setSize(650, 300);
        setVisible(true);
    }

    /**
     *
     */
    public void addSearchBar() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JTextField searchBar = new JTextField();
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayResults(searchStock.search(searchBar.getText()));
            }
        });
        panel.add(searchBar);
        panel.add(searchButton);
        frame.add(panel, BorderLayout.NORTH);
    }

    /**
     * @param stocks
     */
    public void displayResults(ArrayList<StockChild> stocks) {
        results.removeAll();
        for (StockChild stock : stocks) {
            System.out.println("Search Result: " + stock.getStockAbbr());
            displayRow(stock);
        }
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @param stock
     */
    public void displayRow(StockChild stock) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(stock.getStockName());
        JButton select = new JButton("Select");

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolio.buyStock(stock, 0);
                output.update();
                frame.dispose();
                //output.addStocks();
            }
        });
        panel.setPreferredSize(new Dimension(600, 50));

        panel.add(label);
        panel.add(select);
        results.add(panel);
    }

}
