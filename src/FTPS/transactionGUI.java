package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class TransactionGUI extends JFrame {
        JFrame frame = this;
        JPanel info;
        public TransactionGUI(Portfolio p){
                frame.add(new JLabel("Transactions"), BorderLayout.NORTH);
                info = new JPanel();
                info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
                JScrollPane scrollPane = new JScrollPane(info);
                frame.add(scrollPane, BorderLayout.CENTER);
                setSize(600,300);
                setVisible(true);
                displayData(p.getAssets().GetTransactions());
                JButton refresh = new JButton("Refresh");
                refresh.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        info.removeAll();
                        displayData(p.getAssets().GetTransactions());
                        frame.revalidate();
                        frame.repaint();
                    }
                });
                frame.add(refresh, BorderLayout.SOUTH);
        }
        public void displayData(ArrayList<Transactions> transactions){
                for(Transactions transaction: transactions){
                        displayRow(transaction);
                }
        }
        public void displayRow(Transactions transaction){
                JPanel panel = new JPanel();
                JLabel label = new JLabel(transaction.getInfo() + " " + transaction.getTime());
                panel.add(label);
                info.add(panel);
        }
}
