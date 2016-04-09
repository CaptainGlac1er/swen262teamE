
/**
 * Created by Steven on 4/7/2016.
 */

package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by CaptainGlac1er on 3/31/2016.
 */
public class WatchListGUI extends PageGUI {
    WatchListBackend watchListBackend;
    WatchListUpdater watchListUpdater;
    Portfolio portfolio;
    public WatchListGUI(WatchListBackend backend, WatchListUpdater updater, Portfolio portfolio){
        this.portfolio = portfolio;
        watchListBackend = backend;
        watchListUpdater = updater;
        addComponents();
    }
    @Override
    public void addComponents() {
        panel.setLayout(new BorderLayout());
        //panel.add(addWatchItem());
        JPanel bottom = new JPanel();
        JButton addStock = new JButton("Add Stock");
        addStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new WatchListSearch(new SearchStock(), watchListBackend, portfolio.getWatchList());
            }
        });
        JPanel data = new JPanel();
        data.setLayout(new BoxLayout(data,BoxLayout.Y_AXIS));
        ScrollPane pane = new ScrollPane();
        pane.add(data);
        for(WatchStock w : portfolio.getWatchList().getWatchList()) {
            StockChild c = w.getStockInfo();
            data.add(addWatchItem(c.getStockAbbr() + " ",  c));
        }
        panel.add(pane);
        bottom.add(addStock);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.setBackground(Color.gray);
    }
    public JPanel addWatchItem(String left, StockChild info){
        JPanel panel = new JPanel();
        JLabel ticker = new JLabel(left);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    new StockGUI(info, portfolio);
                }
            }
        });
        panel.add(ticker);

        return panel;
    }

    @Override
    public void update() {
        clear();
        addComponents();
        panel.revalidate();
        panel.repaint();
    }
}
