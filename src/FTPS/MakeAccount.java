package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
public class MakeAccount implements  Order {
        private assets asset;
        private double worth;
        private String name;
        public MakeAccount(assets inAsset, double inWorth, String inName){
            worth = inWorth;
            name = inName;
            this.asset = inAsset;
        }

        public void execute() {
            asset.AddCashAccount(worth,name);
        }
    }