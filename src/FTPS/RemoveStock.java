package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//concrete class for removing stocks
public class RemoveStock implements Order {
    //needed data
    private Assets asset;
    private int quantity;
    private StockChild stock;

    //constructor, set data
    public RemoveStock(Assets inAsset, StockChild inStock, int inNumSold) {
        stock = inStock;
        quantity = inNumSold;
        asset = inAsset;
    }

    public void execute() {
        asset.DelStock(stock, quantity);
    }
    public void undo(){asset.AddStock(quantity,stock);}
}
