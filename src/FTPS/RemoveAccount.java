package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class remove cash account
public class RemoveAccount implements Order {
    //needed data
    private Assets asset;
    private int index;
    private double worth;
    private String name;

    //set data
    public RemoveAccount(Assets inAsset, int inIndex, String inName, double inWorth) {
        index = inIndex;
        worth = inWorth;
        name = inName;
        asset = inAsset;
    }

    //handle execute call
    public void execute() {
        asset.DelCashAccount(index);
    }
    public void undo(){asset.AddCashAccount(worth,name);}
}