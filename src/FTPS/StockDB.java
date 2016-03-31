package FTPS;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Josh on 3/31/2016.
 */
public class StockDB implements Runnable
{
    private ArrayList<StockChild> AllStocks = new ArrayList<>();
    private String tName = "Stock Update Thread";
    private static StockDB DBInstance;

    private StockDB()
    {
        getAllStocks();
    }

    public static StockDB getInstance()
    {
        if (DBInstance != null)
            return DBInstance;

        DBInstance = new StockDB();
        return DBInstance;
    }

    private void getAllStocks() {
        StockChild tempStock;
        String filepath = (new File(FTPS.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + "\\equities.csv";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            while ((currentLine = br.readLine()) != null) {
                String[] info = currentLine.split("\"");
                tempStock = new StockChild(info[3], info[1], info[7], 0);
                AllStocks.add(tempStock);
            }

        } catch (NumberFormatException | IOException e) {
            System.out.println("File read error: " + e);
        }
    }

    public ArrayList<StockChild> getStocks()
    {
        return AllStocks;
    }

    @Override
    public void run()
    {
        double updatedWorth;
        double i = 0;
        System.out.println("Running update thread");

        try {
            for (StockChild child : AllStocks)
            {
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
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                {

                    updatedWorth = parseInput(inputLine);

                    if (updatedWorth != 0 )
                        child.setWorth(updatedWorth);
                }

                // MAKE SURE TO CLOSE YOUR CONNECTION!
                in.close();

                //print progress percentage
                if (i % 20 == 0)
                    System.out.println((int) (i / AllStocks.size() * 100) + "%");
            }
        }

        catch ( IOException ex)
        {
            System.out.println("Stock update thread error" + ex);
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

    private double parseInput(String inFullline)
    {
        double newWorth = 0;

        //split line and remove extra characters from needed "Bid" field
        String[] linearray = inFullline.split("(:)|(,)");
        linearray[18] = linearray[18].replaceAll("[^0-9.]", "");

        //if the bid field is not null
        if (linearray[18] != null)
        {
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

