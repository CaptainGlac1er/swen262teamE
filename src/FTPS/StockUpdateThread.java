package FTPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Josh on 3/23/2016.
 */
public class StockUpdateThread implements Runnable
{
    private ArrayList<StockChild> stockList;
    private String tName;


    public StockUpdateThread( ArrayList<StockChild> inStocks, String inName )
    {
        tName = inName;
        stockList = inStocks;
    }

    @Override
    public void run()
    {
        System.out.println("Running update thread");
        while ( true )
        {
            int i = 0;
            try {
                for (StockChild child : stockList) {
                    System.out.println("Updating stock " + child.getStockAbbr() + "...\n");
                    i++;
                    String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" + child.getStockAbbr().trim() + "%22)&env=store://datatables.org/alltableswithkeys&format=json";

                    // Create a URL and open a connection
                    URL YahooURL = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) YahooURL.openConnection();

                    // Set the HTTP Request type method to GET (Default: GET)
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(100000);
                    con.setReadTimeout(100000);

                    // Created a BufferedReader to read the contents of the request.
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
/*                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        out += inputLine + "\n";
                        response.append(inputLine);
                    }
    */
                    // MAKE SURE TO CLOSE YOUR CONNECTION!
                    in.close();
                    if (i % 20 == 0)
                        System.out.println((int) (((double) i) / stockList.size() * 100) + "%");
                    // response is the contents of the XML
                    //System.out.println(response.toString());
                }

                System.out.println("Stock update finished.");
                Thread.sleep(40000);
            }

            catch ( IOException | InterruptedException ex)
            {
                System.out.println("Stock update thread error" + ex);
            }
        }
    }

    public void start()
    {
        Thread stockT;

        System.out.println("*** Starting stock thread. ***");
        stockT = new Thread( this, tName);
        stockT.start();
    }
}
