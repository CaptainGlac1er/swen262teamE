package FTPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Josh on 3/23/2016.
 */
public class StockUpdateThread implements Runnable
{
    private ArrayList<StockChild> stockList;
    private String tName;

    ArrayList<String> response = new ArrayList<>();


    public StockUpdateThread( ArrayList<StockChild> inStocks, String inName )
    {
        tName = inName;
        stockList = inStocks;
    }

    @Override
    public void run()
    {
        double updatedWorth;
        System.out.println("Running update thread");
        while ( true )
        {
            int i = 0;
            try {
               for (StockChild child : stockList) {
                    i++;
                    String url = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" + child.getStockAbbr().trim() + "%22)&env=store://datatables.org/alltableswithkeys&format=json";
//System.out.println(child.getStockAbbr() + ": " + child.getWorth());

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
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {

                        updatedWorth = parseInput(inputLine);

                        if (updatedWorth != 0 )
                            child.setWorth(updatedWorth);
                    }

                    // MAKE SURE TO CLOSE YOUR CONNECTION!
                    in.close();
                    if (i % 20 == 0)
                        System.out.println((int) (((double) i) / stockList.size() * 100) + "%");
                    // response is the contents of the XML
                    //System.out.println(response.toString());
//System.out.println(child.getStockAbbr() + ": " + child.getWorth());


                   Thread.sleep(2000);
                }
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

        //print message to screen and begin running thread
        System.out.println("*** Starting stock thread. ***");
        stockT = new Thread( this, tName);
        stockT.start();
    }

    private double parseInput(String inFullline){
        double newWorth = 0;

        //split line and remove extra characters from needed "Bid" field
        String[] linearray = inFullline.split("(:)|(,)");
        linearray[18] = linearray[18].replaceAll("[^0-9.]", "");

        //if the bid field is not null
        if (linearray[18] != null){
            try
            {
                //convert string to double and store
                newWorth = Double.parseDouble(linearray[18]);
            }
            catch(NumberFormatException e)
            {
                System.out.println("Error converting new worth" + e);
            }
        }
        return newWorth;
    }
}
