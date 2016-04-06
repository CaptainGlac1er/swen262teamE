package FTPS;

/**
 * Created by CaptainGlac1er on 4/4/2016.
 */
public class DepositMoney implements Order {
    CashAccount cashAccount;
    double amount;
    public DepositMoney(CashAccount c, double amount){
        this.amount = amount;
        cashAccount = c;
    }
    @Override
    public void execute() {
        cashAccount.addCash(amount);
    }

    @Override
    public void undo() {
        cashAccount.removeCash(amount);
    }
}