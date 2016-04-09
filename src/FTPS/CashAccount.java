package FTPS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */

//user cash accounts
public class CashAccount {

    ArrayList<Component> componentsToUpdate = new ArrayList<>();
    //name and value
    private double accountWorth;
    private String accountName;

    //constructor for cashAccounts, name and value set
    public CashAccount(double inWorth, String inName) {
        accountName = inName;
        accountWorth = inWorth;
    }

    //return the name of Acct
    public String getAccountName() {
        String nameTemp = accountName;
        return nameTemp;
    }

    //return the value of the Acct
    public double getAccountWorth() {
        double worthTemp = accountWorth;
        return worthTemp;
    }

    //add money
    public void addCash(double inAmount) {
        accountWorth += inAmount;
        update();

    }

    //remove money
    public void removeCash(double inAmount) {
        accountWorth -= inAmount;
        update();
    }

    //update gui
    public void update() {
        accountWorth = ((int) (accountWorth * 100)) / 100.0;
        for (Component c : componentsToUpdate) {
            ((JLabel) c).setText(accountWorth + "");
        }
    }

    /**
     * Adds the component to an array of objects to update when the account balance changes
     *
     * @param component component to update Balance
     */
    public void addAccountBalanceViewer(Component component) {
        componentsToUpdate.add(component);
    }
}
