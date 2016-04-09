package FTPS;

import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
//interface for composite stocks
public interface Stock {
    //stock methods
    //return index
    List<StockChild> getIndexStocks();

    //add index
    void addIndexStock(StockChild s);

    //remove index
    void removeIndexStock(StockChild s);

    //Return total worth of owned stock
    double getTotWorth();

    //return worth of one stock
    double getWorth();

    double getProjWorth();

    //set the stock worth (projections)
    void setProjWorth(double inWorth);

    //Return the count of stocks owned
    int getCount();

    //Increment the count of stocks owned
    void incCount(int quantity);

    //Decrement the count of stocks owned
    void decCount(int quantity);

    //return stock name
    String getStockName();

    //return stock abbreviation
    String getStockAbbr();

    //return stock index
    String getStockIndex();

}