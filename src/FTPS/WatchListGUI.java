package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by CaptainGlac1er on 3/31/2016.
 */
public class WatchListGUI extends PageGUI {
    WatchListBackend watchListBackend;
    WatchListUpdater watchListUpdater;
    public WatchListGUI(WatchListBackend backend, WatchListUpdater updater){
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
        bottom.add(addStock);
        panel.add(bottom, BorderLayout.SOUTH);
        panel.setBackground(Color.gray);
    }
    public JPanel addWatchItem(String left, String right, StockChild info){
        JPanel panel = new JPanel();
        JLabel ticker = new JLabel(left);
        JLabel price = new JLabel(right);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    new StockGUI(info);
                }
            }
        });
        panel.add(ticker);
        panel.add(price);

        return panel;
    }
}
