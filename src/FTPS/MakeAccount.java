package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class for making cash accounts
public class MakeAccount implements Order {
    //needed data
    private Assets asset;
    private double worth;
    private String name;
    private int index;

    //constructor, sets data
    public MakeAccount(Assets inAsset, double inWorth, String inName,int inIndex) {
        worth = inWorth;
        name = inName;
        asset = inAsset;
        index = inIndex;
    }

    //handles execute call
    public void execute() {
        asset.AddCashAccount(worth, name);
    }
    public void undo(){
        asset.DelCashAccount(index);
    }
}