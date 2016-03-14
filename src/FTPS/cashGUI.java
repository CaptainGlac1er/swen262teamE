package FTPS;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class CashGUI extends JFrame {
    private List<CashAccount> cashAccounts;
    private JPanel cashList;
    private JFrame frame = this;

    /**
     *
     * @param cashAccounts
     */
    public CashGUI(List<CashAccount> cashAccounts) {
        this.cashAccounts = cashAccounts;
        cashList = new JPanel();
        generateAccountsList();
        JLabel topLabel = new JLabel("Cash Accounts");
        this.add(topLabel, BorderLayout.NORTH);
        this.add(cashList, BorderLayout.CENTER);
        JButton addAccount = new JButton("Add Account");
        addAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CashAccount newCashAccount = new CashAccount(0, "New Account");
                cashAccounts.add(newCashAccount);
                JOptionPane.showMessageDialog(null, "New Account Added");
                addAccountRow(cashList, newCashAccount);
                generateAccountsList();
            }
        });
        this.add(addAccount, BorderLayout.SOUTH);
        this.setSize(600, 300);
        setVisible(true);

    }

    /**
     * Generates the list of cash accounts on the Jframe
     */
    public void generateAccountsList() {
        cashList.removeAll();
        cashList.setLayout(new BoxLayout(cashList, BoxLayout.Y_AXIS));
        for (CashAccount account : cashAccounts) {
            System.out.println(account.getAccountName() + " loaded");
            cashList.add(addAccountRow(new JPanel(new GridLayout(0, 5)), account));
        }
        frame.revalidate();
        frame.repaint();


    }

    /**
     *
     * @param panel
     * @param account
     * @return
     */
    public JPanel addAccountRow(JPanel panel, CashAccount account) {
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
        return panel;
    }
}
