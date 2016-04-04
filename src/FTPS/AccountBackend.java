package FTPS;

import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/24/2016.
 */
public class AccountBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    Portfolio portfolio;
    public AccountBackend(PageUpdater pageUpdater, Portfolio portfolio){
        this.portfolio = portfolio;
        this.pageUpdater = pageUpdater;
        setGUI(new AccountGUI(this, new AccountUpdater()));
    }

    @Override
    public void update() {

    }
    public ArrayList<CashAccount> getAccounts(){
        return portfolio.getAssets().GetAccounts();
    }
    public void makeAccount(String name){
        System.out.println("made account " + name);
        //portfolio.addAcct(0,portfolio.getAssets().GetAccounts().size(), name);
    }
}
