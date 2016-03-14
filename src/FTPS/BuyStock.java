package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class for buying stock
public class BuyStock implements Order {
    //data needed
    private Assets asset;
    private int quantity;
    private StockChild stock;

    //constructor, sets data
    public BuyStock(Assets inAsset, StockChild inStock, int inQuantity) {
        stock = inStock;
        quantity = inQuantity;
        asset = inAsset;
    }

    //handle execute call
    public void execute() {
        asset.AddStock(quantity, stock);
    }
}
