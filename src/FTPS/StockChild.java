package FTPS;

/**
 * Created by Josh on 3/3/2016.
 */
public class StockChild extends StockParent {

    private int stockCount;

    public StockChild (String inName, String inAbbr, String inIndex, int inCount, double inWorth){
        super (inName, inAbbr, inIndex, inWorth);
        stockCount = inCount;
    }

    //Return total worth of owned stock
    public double GetTotWorth() {
        return (super.getWorth() * stockCount);
    }

    //Return the count of stocks owned
    public int GetCount(){
        return stockCount;
    }

    //Increment the count of stocks owned
    public void IncCount(){
        stockCount++;
    }

    //Decrement the count of stocks owned
    public void DecCount() {
        stockCount--;
    }
}
