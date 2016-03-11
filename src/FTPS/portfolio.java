package FTPS;

import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.lang.System;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Portfolio {
    User user;
    Assets assets = new Assets();
    PortEngine portEngine;
    public Portfolio(User user){
        this.portEngine = new PortEngine();
        this.user = user;
        loadPortfolio();
        new portfolioGUI(user, this);
    }
    public Assets getAssets(){
        return assets;
    }
    public void createAsset(){

    }
    public void getPortfolio(){

    }
    public void getUser(String username){

    }
    //handle buying stock, call invoker
    public void buyStock(StockChild inStock, int inQuantity){
        BuyStock bStock = new BuyStock(assets,inStock,inQuantity);
        placeOrder(bStock);
    }
    //handle selling stock, call invoker
    public void sellStock(StockChild inStock, int inNumSold){
        RemoveStock rStock = new RemoveStock(assets,inStock,inNumSold);
        placeOrder(rStock);
    }
    //handle cash account creation, call invoker
    public void addAcct(double inWorth, String inName){
        MakeAccount mAcct = new MakeAccount(assets,inWorth,inName);
        placeOrder(mAcct);
    }
    //handle cass account removal, call invoker
    public void remAcct(int inIndex){
        RemoveAccount rAcct = new RemoveAccount(assets, inIndex);
        placeOrder(rAcct);
    }
    //command invoker
    public void placeOrder(Order order) {
        order.execute();
    }
    public boolean loadPortfolio(){
        JFileChooser f = new JFileChooser();
        File dir = new File(f.getCurrentDirectory().toString().concat("\\Users"));
        java.lang.System.out.println(dir.getAbsolutePath());
        dir.mkdir();
        File checkfile = new File(dir, user.getUsername() + "Port.txt");
        if (!checkfile.exists()) {
            try {
                PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
                writer.println("1");
                writer.println("1");
                writer.println("a");
                writer.println("Main");
                writer.println("0");
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String currentLine;
            //BufferedReader br = new BufferedReader(new FileReader(checkfile));
            Scanner scanner = new Scanner(checkfile);
            int topLevelEntries = Integer.parseInt(scanner.nextLine());
            for(int a = 0; a < topLevelEntries; a++){
                int amount = Integer.parseInt(scanner.nextLine());
                for(int b = 0; b < amount; b++) {

                    String input = scanner.nextLine();
                    System.out.println("reading " + input);
                    char type = input.charAt(0);
                    switch (type) {
                        case 'a':
                            String accountName = scanner.nextLine();
                            double balance = Double.parseDouble(scanner.nextLine());
                            assets.AddCashAccount(balance, accountName);
                            break;
                        case 's':
                            String ticker = scanner.nextLine();
                            int count = Integer.parseInt(scanner.nextLine());
                            assets.AddStock(count,portEngine.search(ticker));
                            break;
                    }
                }

            }
            /*while((currentLine = br.readLine()) != null){
                switch (currentLine){
                    case "a":
                        for()
                        break;
                    case "s":
                        break;
                }
            }*/
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
        return true;
    }
}
