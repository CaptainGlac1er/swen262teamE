package FTPS;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/10/2016.
 */
public class SearchStock
{
    ArrayList<StockChild> stocks;
    public SearchStock(ArrayList<StockChild> stocks){
        this.stocks = stocks;
    }
    public ArrayList<StockChild> search(String ticker){
        ArrayList<StockChild> output = new ArrayList<>();
        for(StockChild s: stocks)
            if(s.getStockAbbr().contains(ticker) || s.getStockName().contains(ticker))
                output.add(s);
        return output;

    }
    public StockChild getStock(String ticker){
        for(StockChild s: stocks)
            if(s.getStockAbbr().equals(ticker))
                return s;
        return null;
    }

    public ArrayList<StockChild> search(ArrayList<StockChild> stocks, String abbr, String name,
                                        String index)
    {
        List<StockChild> currList = new ArrayList<>();

        for( StockChild currStock : stocks ) {
            //if the stock is not already in the list
            if (!(stocks.contains(currStock)))
            {
                //check that stock name partially matches
                if (currStock.getStockName().contains(name))
                {
                    currList.add(currStock);
                }
            }

            //if the stock is not already in the list
            if (!(stocks.contains(currStock)))
            {
                //check that stock abbr partially matches
                if (currStock.getStockAbbr().contains(abbr))
                {
                    currList.add(currStock);
                }
            }

            //if the stock is not already in the list
            if (!(stocks.contains(currStock)))
            {
                //check that stock index partially matches
                if (currStock.getStockIndex().contains(index))
                {
                    currList.add(currStock);
                }
            }
        }

        java.lang.System.out.println(stocks);

        return stocks;
    }
}
