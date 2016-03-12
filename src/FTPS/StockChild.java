package FTPS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
public class StockChild extends StockParent implements Stock {

    private int stockCount;
    private List<StockChild> indexStocks;

    public StockChild(String inName, String inAbbr, String inIndex, int inCount, double inWorth) {
        super(inName, inAbbr, inIndex, inWorth);
        stockCount = inCount;
        stockName = inName;
        stockAbbr = inAbbr;
        stockWorth = inWorth;
        stockIndex = inIndex;
        indexStocks = new ArrayList<StockChild>();
    }

    public List<StockChild> getIndexStocks() {
        return indexStocks;
    }

    public void addIndexStock(StockChild s) {
        indexStocks.add(s);
    }

    public void removeIndexStock(StockChild s) {
        indexStocks.remove(s);
    }

    //Return total worth of owned stock
    public double GetTotWorth() {
        return (super.getWorth() * stockCount);
    }

    //Return the count of stocks owned
    public int GetCount() {
        return stockCount;
    }

    //Increment the count of stocks owned
    public void IncCount() {
        stockCount++;
    }

    //override for an amt
    public void IncCount(int quantity) {
        stockCount += quantity;
    }

    //Decrement the count of stocks owned
    public void DecCount() {
        stockCount--;
    }

    //override for an amt
    public void DecCount(int quantity) {
        stockCount -= quantity;
    }

    public String getStockAbbr() {
        return ("Abbr :" + stockAbbr);
    }

    public String getStockIndex() {
        return ("Index :" + stockIndex);
    }

    public

}
