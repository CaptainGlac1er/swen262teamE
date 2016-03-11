package FTPS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class cashGUI extends JFrame {
    private List<cashAccount> cashAccounts;
    public cashGUI(List<cashAccount> cashAccounts){
        this.cashAccounts = cashAccounts;
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel, BorderLayout.NORTH);
        this.add(getAccountsList(), BorderLayout.CENTER);


        this.setSize(600,300);
        setVisible(true);

    }
    public JPanel getAccountsList(){
        JPanel panel = new JPanel(new GridLayout(cashAccounts.size(),2));
        for(cashAccount account : cashAccounts){
            JLabel name = new JLabel(account.GetAccountName());
            JLabel balance = new JLabel(account.GetAccountWorth() + "");
            JTextField change = new JTextField("");
            JButton deposit = new JButton("Deposit");
            JButton withdraw = new JButton("Withdraw");
            account.addAccountBalanceViewer(balance);
            deposit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Double amount = Double.parseDouble(change.getText());
                    account.AddCash(amount);
                }
            });
            withdraw.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Double amount = Double.parseDouble(change.getText());
                    account.RemoveCash(amount);
                }
            });
            panel.add(name);
            panel.add(balance);
            panel.add(change);
            panel.add(deposit);
            panel.add(withdraw);

        }
        return panel;
    }
}
