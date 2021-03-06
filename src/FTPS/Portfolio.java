package FTPS;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Portfolio {
    User user;
    Assets assets = new Assets();
    PortEngine portEngine;
    Stack undoStack = new Stack();
    Stack redoStack = new Stack();
    PortfolioBackend portfolioBackend;

    public WatchListStock getWatchList() {
        return watchList;
    }

    WatchListStock watchList;
    public Portfolio(User user, PortfolioBackend portfolioBackend) {
        this.portfolioBackend = portfolioBackend;
        this.portEngine = new PortEngine();
        watchList = new WatchListStock(this);
        this.user = user;
        loadPortfolio();
    }
    public void update(){
        portfolioBackend.update();
    }

    public Assets getAssets() {
        return assets;
    }

    //handle buying stock, call invoker
    public void buyStock(StockChild inStock, int inQuantity) {
        BuyStock bStock = new BuyStock(assets, inStock, inQuantity);
        undoStack.push(bStock);
        redoStack.clear();
        placeOrder(bStock);
    }

    //handle selling stock, call invoker
    public void sellStock(StockChild inStock, int inNumSold) {
        RemoveStock rStock = new RemoveStock(assets, inStock, inNumSold);
        undoStack.push(rStock);
        redoStack.clear();
        placeOrder(rStock);
    }

    //handle cash account creation, call invoker
    public void addAcct(double inWorth,int inIndex, String inName) {
        MakeAccount mAcct = new MakeAccount(assets, inWorth, inName, inIndex);
        undoStack.push(mAcct);
        placeOrder(mAcct);
    }

    //handle cash deposits
    public void depositCash(CashAccount c, double amount){
        DepositMoney dAcct = new DepositMoney(c, amount);
        undoStack.push(dAcct);
        redoStack.clear();
        placeOrder(dAcct);
    }

    //handle cass account removal, call invoker
    public void remAcct(int inIndex, int inWorth, String inName) {
        RemoveAccount rAcct = new RemoveAccount(assets, inIndex,inName,inWorth );
        undoStack.push(rAcct);
        redoStack.clear();
        placeOrder(rAcct);
    }


    //Checks to see if there are undoable commands, gets the most recent command, pops it, adds it to redo stack, calls undo
    public void undo(){
        if(undoStack.size() > 0 ) {
            Object o = undoStack.lastElement();
            System.out.println(o.toString());
            if (o instanceof RemoveAccount) {
                int size = undoStack.size() - 1;
                RemoveAccount rmAcct = (RemoveAccount) undoStack.pop();
                redoStack.add(rmAcct);
                placeUndo(rmAcct);
            }
            if (o instanceof MakeAccount) {
                MakeAccount mAcct = (MakeAccount) undoStack.pop();
                redoStack.add(mAcct);
                placeUndo(mAcct);
            }
            if (o instanceof BuyStock) {
                BuyStock bStock = (BuyStock) undoStack.pop();
                redoStack.add(bStock);
                placeUndo(bStock);
            }
            if (o instanceof RemoveStock) {
                RemoveStock rStock = (RemoveStock) undoStack.pop();
                redoStack.add(rStock);
                placeUndo(rStock);
            }
        }
    }

    //Checks to see if there are redoable commands, gets the most recent undone command, pops it, adds it to undo stack, calls execute to redo
    public void redo(){
        if(redoStack.size() > 0) {
            if (redoStack.lastElement() instanceof RemoveAccount) {
                RemoveAccount rmAcct = (RemoveAccount) redoStack.pop();
                undoStack.add(rmAcct);
                placeOrder(rmAcct);
            }
            if (redoStack.lastElement() instanceof MakeAccount) {
                MakeAccount mAcct = (MakeAccount) redoStack.pop();
                undoStack.add(mAcct);
                placeOrder(mAcct);
            }
            if (redoStack.lastElement() instanceof BuyStock) {
                BuyStock bStock = (BuyStock) redoStack.pop();
                undoStack.add(bStock);
                placeOrder(bStock);
            }
            if (redoStack.lastElement() instanceof RemoveStock) {
                RemoveStock rStock = (RemoveStock) redoStack.pop();
                undoStack.add(rStock);
                placeOrder(rStock);
            }
        }
    }
    //command invokers
    public void placeOrder(Order order) {
        order.execute();
        portfolioBackend.update();
    }
    public void placeUndo(Order order) {
        order.undo();
        portfolioBackend.update();
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
                        case 'w':
                            String WatchTicker = scanner.nextLine();
                            watchList.addStockToWatchList(searchStock.getStock(WatchTicker), 0,0);
                            break;
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }
    public void importPortfolio(){
        JFileChooser f = new JFileChooser();
        f.setDialogTitle("Import Account");
        int userSelection = f.showOpenDialog(new JFrame());
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File checkfile = new File(f.getSelectedFile().getAbsolutePath());
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
                            case 'w':
                                String WatchTicker = scanner.nextLine();
                                watchList.addStockToWatchList(searchStock.getStock(WatchTicker), 0,0);
                                break;
                        }
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
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
            writer.println("4");
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
            int watchCount = watchList.getWatchList().size();
            writer.println(watchCount);
            for (WatchStock w : watchList.getWatchList()) {
                writer.println("w");
                StockChild s = w.getStockInfo();
                writer.println(s.getStockAbbr());
                writer.println(s.getCount());
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Portfolio Saved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get total worth
    public double totalValue(){
        double total = 0;
        for(CashAccount c : assets.GetAccounts())
            total += c.getAccountWorth();
        for(StockChild s : assets.GetStocks())
            total += s.getWorth() * s.getCount();
        return total;
    }
}
