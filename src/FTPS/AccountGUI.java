package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/24/2016.
 */
public class AccountGUI extends PageGUI {
    AccountBackend accountBackend;
    AccountUpdater accountUpdater;
    public AccountGUI(AccountBackend backend, AccountUpdater updater){
        accountBackend = backend;
        accountUpdater = updater;
        panel.setLayout(new BorderLayout());
        addComponents();
    }


    @Override
    public void addComponents() {
        panel.setBackground(Color.yellow);
        JPanel items = new JPanel();
        items.setBackground(new Color(0,0,0,0));
        for(CashAccount c : accountBackend.getAccounts())
            items.add(addAccountPanel(c.getAccountName(), c.getAccountWorth() + "", c));
        panel.add(items, BorderLayout.CENTER);
        JPanel bottomBar = new JPanel();
        JTextField newAccount = new JTextField();
        newAccount.setColumns(20);
        JButton makeAccount = new JButton("New Account");
        makeAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountBackend.makeAccount(newAccount.getText());
            }
        });
        bottomBar.add(newAccount);
        bottomBar.add(makeAccount);
        bottomBar.setBackground(new Color(0,0,0,0));
        panel.add(bottomBar, BorderLayout.SOUTH);

    }
    public JPanel addAccountPanel(String left, String right, CashAccount c){
        JPanel panel = new JPanel();
        JLabel name = new JLabel(left);
        JLabel price = new JLabel(right);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton() == MouseEvent.BUTTON1){
                    new CashAccountGUI(c);
                }
            }
        });

        panel.add(name);
        panel.add(price);
        return panel;
    }
}
