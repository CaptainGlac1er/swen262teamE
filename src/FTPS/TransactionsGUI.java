package FTPS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CaptainGlac1er on 4/2/2016.
 */
public class TransactionsGUI extends PageGUI implements Updatable {
    TransactionsUpdater transactionsUpdater;
    TransactionsBackend transactionsBackend;
    public TransactionsGUI(TransactionsBackend backend, TransactionsUpdater updater){
        transactionsBackend = backend;
        transactionsUpdater = updater;
        addComponents();
    }
    @Override
    public void addComponents() {
        JPanel data = new JPanel();
        data.setLayout(new BoxLayout(data,BoxLayout.Y_AXIS));
        ScrollPane pane = new ScrollPane();
        pane.add(data);
        for(Transactions t : transactionsBackend.getTransactions())
            data.add(item(t.getTime(), t.getType(), t.getInfo()));
        panel.setLayout(new BorderLayout());
        panel.add(pane, BorderLayout.CENTER);
    }
    public JPanel item(String left, String middle, String right){
        JPanel panel = new JPanel(new GridLayout(3,1));
        JLabel leftLabel = new JLabel(left);
        JLabel middleLabel = new JLabel(middle);
        JLabel rightLabel = new JLabel(right);
        panel.add(leftLabel);
        panel.add(middleLabel);
        panel.add(rightLabel);

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
