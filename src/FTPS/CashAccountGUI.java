package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by CaptainGlac1er on 4/4/2016.
 */
public class CashAccountGUI extends JFrame implements Updatable {
    Portfolio portfolio;
    CashAccount info;
    JLabel balance;
    public CashAccountGUI(CashAccount info, Portfolio portfolio){
        this.info = info;
        this.portfolio = portfolio;
        this.setTitle(info.getAccountName());
        JFrame accountFrame = this;
        accountFrame.setPreferredSize(new Dimension(400,300));
        accountFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JLabel accountName = new JLabel(info.getAccountName());
        accountName.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(accountName, BorderLayout.NORTH);
        balance = new JLabel(info.getAccountWorth() +"");
        panel.add(balance);


        JPanel bottom = new JPanel();
        JTextField transaction = new JTextField();
        transaction.setColumns(10);
        bottom.add(transaction);
        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolio.depositCash(info, Double.parseDouble(transaction.getText()));
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

    @Override
    public void update() {
        balance.setText("$" + info.getAccountWorth());

    }
}
