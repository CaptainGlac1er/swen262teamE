package FTPS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
//portfolio contents
public class Assets {

    //store different types of portfolio data
    private ArrayList<StockChild> stockList;
    private List<CashAccount> cashAccountList;
    private ArrayList<Transactions> transactionsList;
    private int accountCount = 0;

    //constructor, initialize storage
    public Assets() {
        stockList = new ArrayList<>();
        cashAccountList = new ArrayList<>();
        transactionsList = new ArrayList<>();
    }

    //create a cash account store it and record it
    public void AddCashAccount(double inWorth, String inName) {
        //account
        CashAccount acct = new CashAccount(inWorth, inName);
        cashAccountList.add(acct);
        accountCount++;

        //transaction
        String info = "Created Acoount: #" + inName + ", " + inWorth;
        Transactions transaction = new Transactions("Account", info);
        transactionsList.add(transaction);

    }

    public void loadCashAccount(double inWorth, String inName) {
        //account
        CashAccount acct = new CashAccount(inWorth, inName);
        cashAccountList.add(acct);
        accountCount++;
    }

    //find a cash account, remove it and record it
    public void DelCashAccount(int inAccountIndex) {
        //transaction
        String info = "Removed Acoount: #" + cashAccountList.get(inAccountIndex).getAccountName() + ", " + cashAccountList.get(inAccountIndex).getAccountWorth();
        Transactions transaction = new Transactions("Account", info);
        transactionsList.add(transaction);

        //account
        cashAccountList.remove(inAccountIndex);
    }

    public void loadStock(int quality, StockChild stockChild) {
        stockChild.incCount(quality);
        stockList.add(stockChild);
    }

    //buy a stock,pay for it, store it and record itin each param of a stock and create a temp stock first thing
    public void AddStock(int quantity, StockChild stock) {
        //currently takes in stock object, could take
        double cost = stock.getWorth() * quantity;
        //check cash accounts sequentially
        //if any acct worth > cost, remove cost
        //add the stock to stock list, if exists, inc count
        boolean trigger = false;
        //for every account
        for (int i = 0; i < cashAccountList.size(); i++) {
            if (trigger) {
                break;
            }
            //check to see if have enough cash to buy
            CashAccount acct = cashAccountList.get(i);
            double worth = acct.getAccountWorth();
            boolean doTransaction = CheckValues(worth, cost);
            System.out.println(cost + " " + worth);
            //have enough cash
            if (doTransaction) {
                acct.removeCash(cost);
                stock.incCount(quantity);
                //if already own stock, update, record transaction
                if (stockList.contains(stock)) {
                    int position = stockList.indexOf(stock);
                    StockChild tempStock = stockList.get(position);
                    tempStock.incCount(quantity);
                    stockList.set(position, tempStock);
                    String count = Integer.toString(stock.getCount());
                    String amt = Double.toString(stock.getWorth());
                    String info = ("Bought Stock" + stock.getStockAbbr() + ", Valued at: $" + amt + ", Quantity #: " + count);
                    Transactions transaction = new Transactions("Stock", info);
                    transactionsList.add(transaction);
                } else {
                    //if new stock purchase, record transaction
                    stockList.add(stock);
                    String count = Integer.toString(stock.getCount());
                    String amt = Double.toString(stock.getWorth());
                    String info = ("Bought Stock " + stock.getStockAbbr() + ", Valued at: $" + amt + ", Quantity #: " + count);
                    Transactions transaction = new Transactions("Stock", info);
                    transactionsList.add(transaction);
                }
                trigger = true;
            }
        }
        stock.updateObservers();

    }

    //sell a stock,get paid for it, remove it and record it
    //currently takes in stock object, could take in each param of a stock and create a temp stock first thing
    public void DelStock(StockChild stock, int numSold) {
        //get stock worth
        //add stock worth to an acct
        //remove stock from stocklist
        int acctSize = cashAccountList.size();
        if (acctSize > 0) {
            //get worth
            double worth = stock.getWorth() * numSold;
            CashAccount tempAcct = cashAccountList.get(0);
            tempAcct.addCash(worth);
            cashAccountList.set(0, tempAcct);
            //if sell all
            if (numSold == stock.getCount()) {
                stockList.remove(stock);
            }
            //sell some
            else {
                stock.decCount(numSold);
            }
            //record transaction
            String count = Integer.toString(numSold);
            String amt = Double.toString(stock.getWorth());
            String info = ("Sold Stock " + stock.getStockAbbr() + ", Valued at: $" + amt + ", Quantity #: " + count);
            Transactions transaction = new Transactions("Stock", info);
            transactionsList.add(transaction);
        }
        stock.updateObservers();
    }

    //helper function to reduce clutter
    public boolean CheckValues(double valAcct, double valCost) {
        return valCost <= valAcct;
    }

    public void addTransaction(String type, String info, String dataTime) {
        transactionsList.add(new Transactions(type, info, dataTime));

    }

    //return all of the Assets
    public ArrayList<StockChild> GetStocks() {
        return stockList;
    }

    public List<CashAccount> GetAccounts() {
        return cashAccountList;
    }

    public ArrayList<Transactions> GetTransactions() {
        return transactionsList;
    }
}
