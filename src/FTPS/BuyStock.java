package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
public class BuyStock implements Order {
        private assets asset;
        private int quantity;
        private StockChild stock;
        public BuyStock(assets inAsset, StockChild inStock, int inQuantity){
            stock = inStock;
            quantity = inQuantity;
            this.asset = inAsset;
        }

        public void execute() {
            asset.AddStock(quantity,stock);
        }
}
