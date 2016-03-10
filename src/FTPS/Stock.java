
package FTPS;

        import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
public interface Stock {

    public List<StockChild> getIndexStocks();
    public void addIndexStock(StockChild s);
    public void removeIndexStock(StockChild s);
    //Return total worth of owned stock
    public double GetTotWorth();
    public double getWorth();
    //Return the count of stocks owned
    public int GetCount();

    //Increment the count of stocks owned
    public void IncCount();
    //override for an amt
    public void IncCount(int quantity);
    //Decrement the count of stocks owned
    public void DecCount();
    //override for an amt
    public void DecCount(int quantity);
    public String getStockAbbr();
    public String getStockIndex();

}