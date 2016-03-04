package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class assets {
    ArrayList<stocks> stockList;
    ArrayList<CashAccount> cashAccountList;
    ArrayList<bonds> bondLists;
    ArrayList<transcations> transactionsList;
    private int accountCount = 0;
    public assets(){
        stockList = new ArrayList<stocks>();
        cashAccountList = new ArrayList<CashAccount>();
        bondLists = new ArrayList<bonds>();
        transactionsList = new ArrayList<transcations>();
    }
    public void createCashAccount(int inWorth, String inName){
        CashAccount acct = new CashAccount(inWorth,inName);
        cashAccountList.add(accountCount,acct);
        accountCount++;
    }
    public void removeCashAccount(int inAccountIndex){
        cashAccountList.remove(inAccountIndex);
    }
    public void createStock(){

    }
    public void createBond(){

    }
    public void addStock(){

    }
    public void getAssets(){

    }
}
