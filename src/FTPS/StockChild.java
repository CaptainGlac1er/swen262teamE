package FTPS;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/3/2016.
 */
//concrete class for stock composition, also component
public class StockChild implements Stock {
    //needed data for a composite stock
    private int stockCount;
    private String stockName;
    private String stockAbbr;
    private String stockIndex;
    private double stockWorth;
    private double projWorth;
    private List<StockChild> indexStocks;
    private ArrayList<Component> observers = new ArrayList<>();

    public StockChild(String inName, String inAbbr, String inIndex, int inCount) {
        stockCount = inCount;
        stockName = inName;
        stockAbbr = inAbbr;
        stockIndex = inIndex;
        indexStocks = new ArrayList<>();
        projWorth = 0;
    }

    //get stock children
    public List<StockChild> getIndexStocks() {
        return indexStocks;
    }

    //add child
    public void addIndexStock(StockChild s) {
        indexStocks.add(s);
    }

    //remove child
    public void removeIndexStock(StockChild s) {
        indexStocks.remove(s);
    }

    //Return total worth of owned stock
    public double getTotWorth() {
        return (stockWorth * stockCount);
    }

    //return worth of one stock
    public double getWorth() {
        return stockWorth;
    }

    //return worth of one stock
    public void setWorth(double inWorth) {
        stockWorth = inWorth;
    }

    //return stock name
    public String getStockName() {
        return stockName;
    }

    //Return the count of stocks owned
    public int getCount() {
        return stockCount;
    }

    public double getProjWorth() {
        return ((int) (projWorth * 100)) / 100.0;
    }

    public void setProjWorth(double inWorth) {
        projWorth = ((int) (inWorth * 100)) / 100.0;
    }

    //Increment the count of stocks owned
    public void incCount(int quantity) {
        stockCount += quantity;
    }

    //Decrement the count of stocks owned
    public void decCount(int quantity) {
        stockCount -= quantity;
    }

    //return stock abbreviation
    public String getStockAbbr() {
        return stockAbbr;
    }

    //return stock index name
    public String getStockIndex() {
        return stockIndex;
    }

    public void addObserver(Component component) {
        observers.add(component);
    }

    public void updateObservers() {
        for (Component c : observers) {
            ((JLabel) c).setText(getWorth() + "x" + getCount() + "=" + getTotWorth());
        }
    }
}
