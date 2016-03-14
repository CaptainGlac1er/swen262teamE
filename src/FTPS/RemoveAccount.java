package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class remove cash account
public class RemoveAccount implements Order {
    //needed data
    private Assets asset;
    private int index;

    //set data
    public RemoveAccount(Assets inAsset, int inIndex) {
        index = inIndex;
        asset = inAsset;
    }

    //handle execute call
    public void execute() {
        asset.DelCashAccount(index);
    }
}