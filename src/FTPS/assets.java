package FTPS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
//portfolio contents
public class Assets {

    //store different types of portfolio data
    private List<Stock> stockList;
    private List<CashAccount> cashAccountList;
    private List<Transactions> transactionsList;
    private int accountCount = 0;

    //constructor, initialize storage
    public Assets(){
        stockList = new ArrayList<Stock>();
        cashAccountList = new ArrayList<CashAccount>();
        transactionsList = new ArrayList<Transactions>();
    }
    //create a cash account store it and record it
    public void AddCashAccount(double inWorth, String inName){
        //account
        CashAccount acct = new CashAccount(inWorth,inName);
        cashAccountList.add(acct);
        accountCount++;

        //transaction
        String info = "Created Acoount: #"+ inName +", "+ inWorth;
        Transactions transaction = new Transactions("Account",info);
        transactionsList.add(transaction);

    }
    //find a cash account, remove it and record it
    public void DelCashAccount(int inAccountIndex){
        //transaction
        String info = "Removed Acoount: #"+cashAccountList.get(inAccountIndex).getAccountName() + ", "+cashAccountList.get(inAccountIndex).getAccountWorth() ;
        Transactions transaction = new Transactions("Account",info);
        transactionsList.add(transaction);

        //account
        cashAccountList.remove(inAccountIndex);
    }

    //buy a stock,pay for it, store it and record itin each param of a stock and create a temp stock first thing
    public void AddStock(int quantity, Stock stock){
    //currently takes in stock object, could take
        stock.incCount(quantity);
        double cost = stock.getTotWorth();
        //check cash accounts sequentially
        //if any acct worth > cost, remove cost
        //add the stock to stock list, if exists, inc count
        boolean trigger = false;
        //for every account
        for (int i = 0; i < cashAccountList.size(); i++) {
            if(trigger == true){
                break;
            }
            //check to see if have enough cash to buy
            CashAccount acct = cashAccountList.get(i);
            double worth = acct.getAccountWorth();
            boolean doTransaction = CheckValues(worth,cost);
            //have enough cash
            if (doTransaction == true) {
                acct.removeCash(cost);
                //if already own stock, update, record transaction
                if(stockList.contains(stock)){
                    int position =stockList.indexOf(stock);
                    Stock tempStock = stockList.get(position);
                    tempStock.incCount(quantity);
                    stockList.set(position,tempStock);
                    String count = Integer.toString(stock.getCount());
                    String amt = Double.toString(stock.getWorth());
                    String info = ("Bought Stock"+ stock.getStockAbbr() +",Valued at: $"+ amt + "Quantity #: " + count) ;
                    Transactions transaction = new Transactions("Stock",info);
                    transactionsList.add(transaction);
                }
                else{
                    //if new stock purchase, record transaction
                   stockList.add(stock);
                    String count = Integer.toString(stock.getCount());
                    String amt = Double.toString(stock.getWorth());
                    String info = ("Bought Stock "+ stock.getStockAbbr() +", Valued at: $"+ amt + ", Quantity #: " + count) ;
                    Transactions transaction = new Transactions("Stock",info);
                    transactionsList.add(transaction);
                }
                trigger = true;
            }
        }

    }

    //sell a stock,get paid for it, remove it and record it
    //currently takes in stock object, could take in each param of a stock and create a temp stock first thing
    public void DelStock(StockChild stock, int numSold){
        //get stock worth
        //add stock worth to an acct
        //remove stock from stocklist
        int acctSize = cashAccountList.size();
        if(acctSize > 0){
            //get worth
            double worth = stock.getWorth() * numSold;
            CashAccount tempAcct = cashAccountList.get(0);
            tempAcct.addCash(worth);
            cashAccountList.set(0, tempAcct);
                //if sell all
                if(numSold == stock.getCount()){
                    stockList.remove(stock);
                }
                //sell some
                else{
                    stock.decCount(numSold);
                }
                //record transaction
                String count = Integer.toString(numSold);
                String amt = Double.toString(stock.getWorth());
                String info = ("Sold Stock " + stock.getStockAbbr() + ", Valued at: $" + amt + ", Quantity #: " + count);
                Transactions transaction = new Transactions("Stock", info);
                transactionsList.add(transaction);
        }
    }
    //helper function to reduce clutter
    public boolean CheckValues(double valAcct, double valCost){
        if(valCost <= valAcct) {
            return true;
        }
        else return false;
    }
    //return all of the Assets
    public List<Stock> GetStocks(){
        return  stockList;
    }
    public List<CashAccount> GetAccounts(){
        return cashAccountList;
    }
    public List<Transactions> GetTransactions(){
        return  transactionsList;
    }
}
