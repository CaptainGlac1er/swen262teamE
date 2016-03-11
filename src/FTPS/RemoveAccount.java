package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
public class RemoveAccount implements Order {
        private assets asset;
        private int index;
        public RemoveAccount(assets inAsset, int inIndex){
            index = inIndex;
            this.asset = inAsset;
        }

        public void execute() {
            asset.DelCashAccount(index);
        }
    }