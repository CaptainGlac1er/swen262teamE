package FTPS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
public class StockChild implements  Stock{

    private int stockCount;
    private String stockName;
    private String stockAbbr;
    private String stockIndex;
    private double stockWorth;
    private List<StockChild> indexStocks;

    public StockChild (String inName, String inAbbr, String inIndex, int inCount, double inWorth){
        stockCount = inCount;
        stockName =  inName;
        stockAbbr = inAbbr;
        stockWorth = inWorth;
        stockIndex = inIndex;
        indexStocks = new ArrayList<StockChild>();
    }
    public List<StockChild> getIndexStocks(){
        return indexStocks;
    }
    public void addIndexStock(StockChild s){
        indexStocks.add(s);
    }
    public void removeIndexStock(StockChild s){
        indexStocks.remove(s);
    }
    //Return total worth of owned stock
    public double GetTotWorth() {
        return (stockWorth * stockCount);
    }
    public double GetWorth() {

    return stockWorth;
    }
    //Return the count of stocks owned
    public int GetCount(){
        return stockCount;
    }

    //Increment the count of stocks owned
    public void IncCount(int quantity){
        stockCount+=quantity;
    }
    //Decrement the count of stocks owned
    //override for an amt
    public void DecCount(int quantity) {
        stockCount -= quantity;
    }
    public String getStockAbbr(){
        return  stockAbbr;
    }
    public String getStockIndex(){
        return stockIndex;
    }

}
