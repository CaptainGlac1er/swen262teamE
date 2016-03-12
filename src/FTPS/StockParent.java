package FTPS;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class StockParent {

    private String stockName;
    private String stockAbbr;
    private String stockIndex;
    private double stockWorth;

    public StockParent (String inName, String inAbbr, String inIndex, double inWorth)
    {
        stockName = inName;
        stockAbbr = inAbbr;
        stockIndex = inIndex;
        stockWorth = inWorth;
    }

    public void setWorth(double inWorth ){ stockWorth = inWorth; }

    public String getStockName() {
        return ("Index :" + stockName);
    }

    public String getStockIndex() {
        return ("Index :" + stockIndex);
    }

    public String getStockAbbr() {
        return ("Abbr :" + stockAbbr);
    }

    public double getWorth(){
        return stockWorth;
    }
}
