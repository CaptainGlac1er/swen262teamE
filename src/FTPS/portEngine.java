package FTPS;

import sun.reflect.generics.tree.ReturnType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class PortEngine {
    //formula data
    private double principal;
    private double years;
    private double worth;
    private double bullRate = .05;
    private double bearRate = -.05;
    //store output
    private ArrayList<StockChild> projectedStocks;
    //calc value change for each stock
    public ArrayList<StockChild> simBullBearNone(ArrayList<StockChild> list,double numYears, int decider){
        for (StockChild stock: list) {
            years = numYears;
            principal = stock.getTotWorth();
            //calculate value or return if none projection
            for (int i = 0; i <= years ; i++) {
                if(decider == 0){
                    worth = principal * Math.pow(1+bullRate,i);
                }
                if(decider == 1){
                    worth = principal * Math.pow(1+bearRate,i);
                }
                else{
                    return list;
                }

            }
            //add projected worth
            stock.setProjWorth(worth);
            //return stock list with projected stock data changed
            projectedStocks.add(stock);
        }
        return projectedStocks;
    }

    //calculate number of years to sim
    public double calcYears(double inDays, double inMonths, double inYears ){
        double days = inDays;
        days += (inMonths * 30);
        days += (inYears * 365);
        years = (days/365);
        return years;

    }
    //reset data for a sim
    public void clear(){
        principal = 0;
        years = 0;
        worth = 0;
        projectedStocks.clear();
    }
    public Stock search(String searchString){
        return null;
    }
}
