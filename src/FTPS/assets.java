package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
//portfolio contents
public class Assets {

    //store different types of portfolio data
    private ArrayList<StockChild> stockList;
    private ArrayList<CashAccount> cashAccountList;
    private ArrayList<Transactions> transactionsList;
    private int accountCount = 0;

    //constructor, initialize storage
    public Assets(){

        stockList = new ArrayList<StockChild>();
        cashAccountList = new ArrayList<CashAccount>();
        transactionsList = new ArrayList<Transactions>();
    }
    //create a cash account store it and record it
    public void AddCashAccount(double inWorth, String inName){
        //account
        CashAccount acct = new CashAccount(inWorth,inName);
        cashAccountList.add(accountCount,acct);
        accountCount++;

        //transaction
        String info = "Created Acoount: #"+ inName +", "+ inWorth;
        Transactions transaction = new Transactions(info);
        transactionsList.add(transaction);

    }
    //find a cash account, remove it and record it
    public void DelCashAccount(int inAccountIndex){
        //transaction
        String info = "Removed Acoount: #"+cashAccountList.get(inAccountIndex).GetAccountName() + ", "+cashAccountList.get(inAccountIndex).GetAccountWorth() ;
        Transactions transaction = new Transactions(info);
        transactionsList.add(transaction);

        //account
        cashAccountList.remove(inAccountIndex);
    }

    //buy a stock,pay for it, store it and record it
    public void AddStock(){

    }
    //sell a stock,get paid for it, remove it and record it
    public void DelStock(){

    }

    //return all of the assets
    public void GetAssets(){

    }
}
