package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class assets {

    private ArrayList<StockChild> stockList;
    private ArrayList<cashAccount> cashAccountList;
    private ArrayList<Transactions> transactionsList;
    private int accountCount = 0;

    public assets(){
        stockList = new ArrayList<StockChild>();
        cashAccountList = new ArrayList<cashAccount>();
        transactionsList = new ArrayList<Transactions>();
    }
    public void AddCashAccount(int inWorth, String inName){
        cashAccount acct = new cashAccount(inWorth,inName);
        cashAccountList.add(accountCount,acct);
        accountCount++;
    }
    public void DelCashAccount(int inAccountIndex){
        cashAccountList.remove(inAccountIndex);
    }
    public void CreateStock(){

    }
    public void AddStock(){

    }
    public void GetAssets(){

    }
}
