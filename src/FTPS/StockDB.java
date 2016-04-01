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
    private static StockDB DBInstance;

    private StockDB()
    {
        //method to load initial stock information
        getAllStocks();
    }

    public static StockDB getInstance()
    {
        //if database has been created
        if (DBInstance != null)

            //return instance of database
            return DBInstance;

        //create database
        DBInstance = new StockDB();

        //return database
        return DBInstance;
    }

    private void getAllStocks() {
        StockChild tempStock;

        //create string for file path to equities file
        String filepath = (new File(FTPS.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + "\\equities.csv";
        try
        {
            String currentLine;

            //open reader
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            //while file has more line
            while ((currentLine = br.readLine()) != null)
            {
                //split lines into array
                String[] info = currentLine.split("\"");

                //create new stock and store in stock array
                try
                {
                    tempStock = new StockChild(info[3], info[1], info[7], Double.parseDouble(info[5]), 0);
                    AllStocks.add(tempStock);
                }

                catch (NumberFormatException ex)
                {
                    System.out.println("Stock not created: " + info[3] + " " + ex );
                }
            }

        }

        catch (NumberFormatException | IOException e)
        {
            System.out.println("File read error: " + e);
        }
    }

    public ArrayList<StockChild> getStocks()
    {
        //get current instance of stock database
        return AllStocks;
    }

    @Override
    public void run()
    {
        double updatedWorth;
        String abbrList = null;
        System.out.println("Running update thread");

        try
        {
            //for all stocks
            for ( StockChild child : AllStocks )
            {
                //if first stock
                if (abbrList == null)
                {
                    //set string to stock abbr
                    abbrList = child.getStockAbbr().trim();
                }
                else
                {
                    //append to string of abbr
                    abbrList += "+" + child.getStockAbbr().trim();
                }
            }

            URL yahoo = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ abbrList + "&f=l1");
            // Create a URL and open a connection
//            URL YahooURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) yahoo.openConnection();

            // Set the HTTP Request type method to GET (Default: GET)
            con.setRequestMethod("GET");
            con.setConnectTimeout(100000);
            con.setReadTimeout(100000);

            // Created a BufferedReader to read the contents of the request.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            //for all stocks
            for ( StockChild child : AllStocks )
            {
                //read next line
                inputLine = in.readLine();

                //store new price
                updatedWorth = parseInput(inputLine);

                //if price existed and converted then store in stock
                if (updatedWorth != 0)
                    child.setWorth(updatedWorth);
            }

            // MAKE SURE TO CLOSE YOUR CONNECTION!
            in.close();


        }

        catch ( IOException ex)
        {
            System.out.println("Stock update thread error" + ex);
        }

        System.out.println("Update complete.");
    }

    public void start()
    {
        Thread stockT;

        //print message to screen and begin running thread
        System.out.println("*** Starting stock thread. ***");
        stockT = new Thread( this, "StockDB Thread");
        stockT.start();
    }

    private double parseInput(String inFullline)
    {
        double newWorth = 0;

        try
        {
            //convert string to double and store
            newWorth = Double.parseDouble(inFullline);
        }

        catch(NumberFormatException e)
        {
            System.out.println("Error converting new worth" + e);
        }

        return newWorth;
    }
}