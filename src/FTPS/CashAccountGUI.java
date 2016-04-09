package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by CaptainGlac1er on 4/4/2016.
 */
public class CashAccountGUI extends JFrame {
    Portfolio portfolio;
    public CashAccountGUI(CashAccount info, Portfolio portfolio){
        this.portfolio = portfolio;
        this.setTitle(info.getAccountName());
        JFrame accountFrame = this;
        accountFrame.setPreferredSize(new Dimension(400,300));
        accountFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JLabel accountName = new JLabel(info.getAccountName());
        accountName.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(accountName, BorderLayout.NORTH);
        JLabel balance = new JLabel(info.getAccountWorth() +"");
        panel.add(balance);


        JPanel bottom = new JPanel();
        JTextField transaction = new JTextField();
        transaction.setColumns(20);
        bottom.add(transaction);
        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolio.depositCash(info, Double.parseDouble(transaction.getText()));
                transaction.setText("$" + info.getAccountWorth());
            }
        });
        JButton withdraw = new JButton("Withdraw");
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info.removeCash(Double.parseDouble(transaction.getText()));
            }
        });
        bottom.add(deposit);
        bottom.add(withdraw);
        panel.add(bottom, BorderLayout.SOUTH);
        accountFrame.add(panel);
        accountFrame.pack();
        accountFrame.setVisible(true);
    }
}
