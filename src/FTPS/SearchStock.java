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
     * @param stocks array of the stocks in the stock database
     */
    public SearchStock(ArrayList<StockChild> stocks) {
        this.stocks = stocks;
    }

    /**
     * Allows the user to search for stocks based on a partial name
     *
     * @param ticker ticker abbreviation of the stock or the name
     * @return an array of Stocks that have the search term
     */
    public ArrayList<StockChild> search(String ticker) {
        ArrayList<StockChild> output = new ArrayList<>();
        for (StockChild s : stocks)
            if (s.getStockAbbr().contains(ticker) || s.getStockName().contains(ticker))
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
        for (StockChild s : stocks)
            if (s.getStockAbbr().equals(ticker))
                return s;
        return null;
    }
}
