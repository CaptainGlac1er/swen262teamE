package FTPS;


import javax.swing.*;
import java.awt.*;
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


        this.setSize(300,400);
        setVisible(true);

    }
    public JPanel getAccountsList(){
        JPanel panel = new JPanel(new GridLayout(cashAccounts.size(),2));
        for(cashAccount account : cashAccounts){
            JLabel name = new JLabel(account.GetAccountName());
            JLabel balance = new JLabel(account.GetAccountWorth() + "");
            panel.add(name);
            panel.add(balance);

        }
        return panel;
    }
}
