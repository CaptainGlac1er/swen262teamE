package FTPS;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Steven on 4/5/2016.
 */
public class WatchListStock {
    private Timer watchListTimer = new Timer();
    private String abrv;
    private double high, low;
    User user;
    WatchStock watchStock;
    ArrayList<WatchStock> watchStocks;


    public WatchListStock(){
        watchStocks = new ArrayList<>();
    }
    public void beginTimer(int time){
        watchListTimer.schedule(new UpdateWatchTask(), time*1000);
    }
    public ArrayList<WatchStock> getWatchList(){
        System.out.println(watchStocks);
        return watchStocks;
    }

    public void addStockToWatchList(StockChild stockChild, double high, double low) {
        //adds to watchlist unless already in that users watchlist
        watchStock = new WatchStock(stockChild, high, low, "none");
        System.out.println(watchStock.getStockInfo().getStockAbbr() + watchStock.getLowTrigger() + watchStock.getTriggertype() + watchStock.getHighTrigger());
        watchStocks.add(watchStock);
        System.out.println("adding stock" + watchStock.getStockInfo());
        System.out.println(watchStocks);

    }
    //function to save watchList to portfoli for user and read from it
    //loadwatchlist
    public void removeFromWatchList(WatchStock watchStock){
        watchStocks.remove(watchStock);
    }
    public void changeHighTrigger(WatchStock watchStock, double newHighTrigger){
        if (watchStocks.contains(watchStock)){
            Integer index = watchStocks.indexOf(watchStock);
            watchStocks.get(index).setHighTrigger(newHighTrigger);
        }
        else{
            System.out.println("Stock not owned");
        }
    }
    public void changeLowTrigger(WatchStock watchStock, double newLowTrigger){
        if (watchStocks.contains(watchStock)){
            Integer index = watchStocks.indexOf(watchStock);
            watchStocks.get(index).setLowTrigger(newLowTrigger);
        }
        else{
            System.out.println("Stock not owned");
        }

    }



    class UpdateWatchTask extends TimerTask{

        public void run(){
            //System.out.println("help");
            //WatchListStock watchListStock = new WatchListStock();
            //WatchListVisitor watchListVisitor = new WatchListVisitor();
            //System.out.println("I made it");
            ArrayList<WatchStock> listOStocks;
            WatchListVisitor watchListVisitor = new WatchListVisitor();
            //System.out.println(listOStocks);
            System.out.println(watchStocks);
            for(WatchStock currentStock : watchStocks){
                currentStock = currentStock.accept(watchListVisitor);
                System.out.println(currentStock.getTriggertype());
                if (currentStock.getTriggertype().equals("high")){
                    System.out.println("This stock's value is higher than the set trigger");

                }
                if (currentStock.getTriggertype().equals("low")){
                    System.out.println("This stock's value is lower than the set trigger");

                }
                if (currentStock.getTriggertype().equals("none")){
                    System.out.println("no difference for this stock");

                }

            }

        }

    }

}
