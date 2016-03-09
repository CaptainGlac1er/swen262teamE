package FTPS;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */

//user cash accounts
public class cashAccount {

    //name and value
    private double accountWorth;
    private String accountName;

    //constructor for cashAccounts, name and value set
    public cashAccount(double inWorth, String inName){
        accountName = inName;
        accountWorth = inWorth;
    }
    public cashAccount(User u){
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
    public void AddCash(double inAmount){
        accountWorth += inAmount;

    }
    public void RemoveCash(double inAmount){
        accountWorth -= inAmount;
    }
}
