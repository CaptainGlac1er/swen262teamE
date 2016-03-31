package FTPS;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Portfolio {
    User user;
    Assets assets = new Assets();
    PortEngine portEngine;

    public Portfolio(User user) {
        this.portEngine = new PortEngine();
        this.user = user;
        loadPortfolio();
        new PortfolioGUI(user, this);
    }

    public Assets getAssets() {
        return assets;
    }

    //handle buying stock, call invoker
    public void buyStock(StockChild inStock, int inQuantity) {
        BuyStock bStock = new BuyStock(assets, inStock, inQuantity);
        placeOrder(bStock);
    }

    //handle selling stock, call invoker
    public void sellStock(StockChild inStock, int inNumSold) {
        RemoveStock rStock = new RemoveStock(assets, inStock, inNumSold);
        placeOrder(rStock);
    }

    //handle cash account creation, call invoker
    public void addAcct(double inWorth, String inName) {
        MakeAccount mAcct = new MakeAccount(assets, inWorth, inName);
        placeOrder(mAcct);
    }

    //handle cass account removal, call invoker
    public void remAcct(int inIndex) {
        RemoveAccount rAcct = new RemoveAccount(assets, inIndex);
        placeOrder(rAcct);
    }

    //command invoker
    public void placeOrder(Order order) {
        order.execute();
    }

    public boolean loadPortfolio() {
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
            SearchStock searchStock = new SearchStock();
            int topLevelEntries = Integer.parseInt(scanner.nextLine());
            for (int a = 0; a < topLevelEntries; a++) {
                int amount = Integer.parseInt(scanner.nextLine());
                System.out.println(amount);
                for (int b = 0; b < amount; b++) {

                    String input = scanner.nextLine();
                    System.out.println("reading " + input);
                    char type = input.charAt(0);
                    switch (type) {
                        case 'a':
                            String accountName = scanner.nextLine();
                            double balance = Double.parseDouble(scanner.nextLine());
                            assets.loadCashAccount(balance, accountName);
                            break;
                        case 's':
                            String ticker = scanner.nextLine();
                            int count = Integer.parseInt(scanner.nextLine());
                            System.out.println(searchStock);
                            assets.loadStock(count, searchStock.getStock(ticker));
                            break;
                        case 't':
                            String tranType = scanner.nextLine();
                            String info = scanner.nextLine();
                            String date = scanner.nextLine();
                            assets.addTransaction(tranType, info, date);
                            break;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @param ifCustomSave
     */
    public void exportPortfolio(boolean ifCustomSave) {
        JFileChooser f = new JFileChooser();
        if (ifCustomSave) {
            f.setDialogTitle("Specify a file to save");

            int userSelection = f.showSaveDialog(new JFrame());

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                savePortfolio(new File(f.getSelectedFile().getAbsolutePath()));
            }

        } else {
            File dir = new File(f.getCurrentDirectory().toString().concat("\\Users"));
            dir.mkdir();
            java.lang.System.out.println(dir.getAbsolutePath());
            File checkfile = new File(dir, user.getUsername() + "Port.txt");
            savePortfolio(checkfile);
        }
    }

    /**
     * save the portfolio to a file
     * @param checkfile file to save too
     */
    public void savePortfolio(File checkfile) {
        try {
            PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
            writer.println("3");
            int accountCount = assets.GetAccounts().size();
            writer.println(accountCount);
            for (CashAccount a : assets.GetAccounts()) {
                writer.println("a");
                writer.println(a.getAccountName());
                writer.println(a.getAccountWorth());
            }
            int stockCount = assets.GetStocks().size();
            writer.println(stockCount);
            for (StockChild s : assets.GetStocks()) {
                writer.println("s");
                writer.println(s.getStockAbbr());
                writer.println(s.getCount());
            }
            int transactionCount = assets.GetTransactions().size();
            writer.println(transactionCount);
            for (Transactions transaction : assets.GetTransactions()) {
                writer.println("t");
                writer.println(transaction.getType());
                writer.println(transaction.getInfo());
                writer.println(transaction.getTime());
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Portfolio Saved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
