
package FTPS;

        import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
public interface Stock {

    List<StockChild> getIndexStocks();
    void addIndexStock(StockChild s);
    void removeIndexStock(StockChild s);
    //Return total worth of owned stock
    double GetTotWorth();
    double GetWorth();
    //Return the count of stocks owned
    int GetCount();

    //Increment the count of stocks owned
    //override for an amt
    void IncCount(int quantity);
    //Decrement the count of stocks owned
    //override for an amt
    //void DecCount(int quantity);
    String getStockAbbr();
    String getStockIndex();

}