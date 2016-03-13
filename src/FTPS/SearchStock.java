package FTPS;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josh on 3/10/2016.
 */
public class SearchStock
{
    public SearchStock(){}

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
