package FTPS;

import java.util.ArrayList;

/**
 * Created by Josh on 3/10/2016.
 */
public class SearchStock {
    ArrayList<StockChild> stocks;

    /**
     * Create SearchStock object used to be able to search the stock database
     *
     */
    public SearchStock() {
        stocks = StockDB.getInstance().getStocks();

    }

    /**
     * Allows the user to search for stocks based on a partial name
     *
     * @param ticker ticker abbreviation of the stock or the name
     * @return an array of Stocks that have the search term
     */
    public ArrayList<StockChild> search(String ticker) {

        //instantiate new output list
        ArrayList<StockChild> output = new ArrayList<>();

        //update stocklist for search
        stocks = StockDB.getInstance().getStocks();

        //for each child in stock list
        for (StockChild s : stocks)

            //if char string is part of ticker or name
            if (s.getStockAbbr().contains(ticker) || s.getStockName().contains(ticker))
                //add current stock to output list
                output.add(s);
        return output;
    }

    /**
     * Get the Stock for that particular ticker abbreviation.
     *
     * @param ticker ticker abbreviation
     * @return the stock
     */
    public StockChild getStock(String ticker) {
        System.out.println("\'" + ticker + "\'");

        //update stocklist for search
        stocks = StockDB.getInstance().getStocks();

        for (StockChild s : stocks)
            if (s.getStockAbbr().equals(ticker))
                return s;
        return null;
    }
}
