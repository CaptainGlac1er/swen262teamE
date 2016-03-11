package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class for making cash accounts
public class MakeAccount implements  Order {
        //needed data
        private Assets asset;
        private double worth;
        private String name;
        //constructor, sets data
        public MakeAccount(Assets inAsset, double inWorth, String inName){
            worth = inWorth;
            name = inName;
            asset = inAsset;
        }
        //handles execute call
        public void execute() {
            asset.AddCashAccount(worth,name);
        }
    }