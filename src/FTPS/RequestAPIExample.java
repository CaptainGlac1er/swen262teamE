package FTPS;

import javax.swing.*;
import java.io.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RequestAPIExample {
    public static void main(String[] args) throws IOException {
        ArrayList<StockChild> children = getAllStocks();
        String out = "";
        int i = 0;
        for(StockChild child: children) {
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
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                out += inputLine + "\n";
                response.append(inputLine);
            }

            // MAKE SURE TO CLOSE YOUR CONNECTION!
            in.close();
            if(i%20 == 0)
                System.out.println((int)(((double)i)/children.size() * 100) + "%");
            // response is the contents of the XML
            //System.out.println(response.toString());
        }
        exportPortfolio(out);
    }
    public static ArrayList<StockChild> getAllStocks() {
        ArrayList<StockChild> AllStocks = new ArrayList<>();
        //String filepath = java.lang.System.getProperty("user.dir") + "\\src\\equities.csv";
        String filepath = (new File(FTPS.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + "\\equities.csv";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            while ((currentLine = br.readLine()) != null) {
                java.lang.System.out.println(currentLine);
                String[] info = currentLine.split("\"");
                java.lang.System.out.println(info[5]);
                StockChild currentStock = new StockChild(info[3], info[1], info[7], 0, Double.parseDouble(info[5]));
                AllStocks.add(currentStock);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return AllStocks;
    }
    public static void exportPortfolio(String out) {
        JFileChooser f = new JFileChooser();
        File dir = new File(f.getCurrentDirectory().toString().concat("\\Stocks"));
        dir.mkdir();
        savePortfolio(new File(dir, ("Stocks.txt")), out);
    }

    /**
     * save the portfolio to a file
     * @param checkfile file to save too
     */
    public static void savePortfolio(File checkfile, String out) {
        try {
            PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
            writer.write(out);
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
