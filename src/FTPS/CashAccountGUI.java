package FTPS;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CaptainGlac1er on 4/4/2016.
 */
public class CashAccountGUI extends JFrame {
    public CashAccountGUI(CashAccount info){
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
        JButton withdraw = new JButton("Withdraw");
        bottom.add(deposit);
        bottom.add(withdraw);
        panel.add(bottom, BorderLayout.SOUTH);
        accountFrame.add(panel);
        accountFrame.pack();
        accountFrame.setVisible(true);
    }
}
