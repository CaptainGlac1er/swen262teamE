package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Assets {

    private ArrayList<StockChild> stockList;
    private ArrayList<CashAccount> cashAccountList;
    private ArrayList<Transactions> transactionsList;
    private int accountCount = 0;

    public Assets(){
        stockList = new ArrayList<StockChild>();
        cashAccountList = new ArrayList<CashAccount>();
        transactionsList = new ArrayList<Transcations>();
    }
    public void AddCashAccount(int inWorth, String inName){
        CashAccount acct = new CashAccount(inWorth,inName);
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
