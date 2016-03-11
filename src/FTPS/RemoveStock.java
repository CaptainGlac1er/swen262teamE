package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
public class RemoveStock implements  Order{
        private assets asset;
        private int quantity;
        private StockChild stock;
        public RemoveStock(assets inAsset, StockChild inStock, int inNumSold){
            stock = inStock;
            quantity = inNumSold;
            this.asset = inAsset;
        }

        public void execute() {
            asset.DelStock(stock,quantity);
        }
    }
