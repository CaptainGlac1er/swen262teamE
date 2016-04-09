package FTPS;


import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 4/2/2016.
 */
public class TransactionsBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    Portfolio portfolio;
    TransactionsGUI transactionsGUI;
    public TransactionsBackend(PageUpdater pageUpdater, Portfolio portfolio){
        this.pageUpdater = pageUpdater;
        this.portfolio = portfolio;
        transactionsGUI = new TransactionsGUI(this, new TransactionsUpdater());
        setGUI(transactionsGUI);
    }
    public ArrayList<Transactions> getTransactions(){
        return portfolio.getAssets().GetTransactions();
    }
    @Override
    public void update() {
        transactionsGUI.update();
    }
}
