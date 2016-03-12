package FTPS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.lang.System;
import java.util.*;
import java.util.List;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class CashGUI extends JFrame {
    private List<CashAccount> cashAccounts;
    private JPanel cashList;
    private JFrame frame = this;
    public CashGUI(List<CashAccount> cashAccounts){
        this.cashAccounts = cashAccounts;
        getAccountsList();
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel, BorderLayout.NORTH);
        this.add(cashList, BorderLayout.CENTER);
        JButton addAccount = new JButton("Add Account");
        addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashAccount newCashAccount = new CashAccount(0, "New Account");
                cashAccounts.add(newCashAccount);
                JOptionPane.showMessageDialog(null, "New Account Added");
                cashList.setLayout(new GridLayout(cashAccounts.size(),2));
                addAccountRow(cashList, newCashAccount);
                cashList.repaint();
                cashList.revalidate();
                frame.repaint();
                frame.revalidate();
                getAccountsList();
            }
        });
        this.add(addAccount, BorderLayout.SOUTH);
        this.setSize(600,300);
        setVisible(true);

    }
    public void getAccountsList(){
        cashList = new JPanel(new GridLayout(cashAccounts.size(),2));
        for(CashAccount account : cashAccounts){
            System.out.println(account.getAccountName() + " loaded");
            addAccountRow(cashList, account);
        }

        JOptionPane.showMessageDialog(null, cashList.getComponents().length +  " accounts loaded");

        /*cashList.revalidate();
        cashList.repaint();
        frame.revalidate();
        frame.repaint();*/
    }
    public void addAccountRow(JPanel panel, CashAccount account){
        JLabel name = new JLabel(account.getAccountName());
        JLabel balance = new JLabel(account.getAccountWorth() + "");
        JTextField change = new JTextField("");
        JButton deposit = new JButton("Deposit");
        JButton withdraw = new JButton("Withdraw");
        account.addAccountBalanceViewer(balance);
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double amount = Double.parseDouble(change.getText());
                account.addCash(amount);
            }
        });
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double amount = Double.parseDouble(change.getText());
                account.removeCash(amount);
            }
        });
        panel.add(name);
        panel.add(balance);
        panel.add(change);
        panel.add(deposit);
        panel.add(withdraw);
    }
}
