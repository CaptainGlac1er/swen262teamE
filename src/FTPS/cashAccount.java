package FTPS;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class CashAccount {
    private int accountWorth;
    private String accountName;

    public CashAccount(int inWorth, String inName){
        accountName = inName;
        accountWorth = inWorth;
    }

    public String getAccountName(){
        String nameTemp = accountName;
        return nameTemp;
    }
    public int getAccountWorth() {
        int worthTemp = accountWorth;
        return worthTemp;
    }
    public void addCash(double amount){
        accountWorth += amount;

    }
    public void removeCash(double amount){
        accountWorth -= amount;
    }
}
