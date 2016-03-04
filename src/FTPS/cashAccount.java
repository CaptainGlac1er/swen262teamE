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

    public String GetAccountName(){
        String nameTemp = accountName;
        return nameTemp;
    }
    public int GetAccountWorth() {
        int worthTemp = accountWorth;
        return worthTemp;
    }
    public void AddCash(double amount){
        accountWorth += amount;

    }
    public void RmoveCash(double amount){
        accountWorth -= amount;
    }
}
