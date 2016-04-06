package FTPS;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by George Walter Colrove IV on 3/24/2016.
 */
public class AccountBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    Portfolio portfolio;
    public AccountBackend(PageUpdater pageUpdater, Portfolio portfolio){
        this.portfolio = portfolio;
        this.pageUpdater = pageUpdater;
        setGUI(new AccountGUI(this, new AccountUpdater(), portfolio));
    }

    @Override
    public void update() {
            getGUI().update();
    }
    public ArrayList<CashAccount> getAccounts(){
        return portfolio.getAssets().GetAccounts();
    }
    public void makeAccount(String name){
        if(!Objects.equals(name, "")) {
            System.out.println("made account " + name);
            portfolio.addAcct(0, portfolio.getAssets().GetAccounts().size(), name);
        }else{
            JOptionPane.showMessageDialog(null, "Please enter another account name");
        }
    }
}
