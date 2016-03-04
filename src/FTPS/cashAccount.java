package FTPS;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */

//user cash accounts
public class CashAccount {

    //name and value
    private double accountWorth;
    private String accountName;

    //constructor for cashAccounts, name and value set
    public CashAccount(double inWorth, String inName){
        accountName = inName;
        accountWorth = inWorth;
    }

    //return the name of Acct
    public String GetAccountName(){
        String nameTemp = accountName;
        return nameTemp;
    }
    //return the value of the Acct
    public double GetAccountWorth() {
        double worthTemp = accountWorth;
        return worthTemp;
    }
    public void AddCash(double amount){
        accountWorth += amount;

    }
    public void RmoveCash(double amount){
        accountWorth -= amount;
    }
}
