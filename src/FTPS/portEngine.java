package FTPS;

import java.util.ArrayList;


/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class PortEngine {
    //formula data
    private double principal;
    private double years;
    private double worth;
    private double Rate = .05;
    //store output
    private ArrayList<StockChild> projectedStocks;

    //calc value change for each stock
    public ArrayList<StockChild> simBullBearNone(ArrayList<StockChild> list, double years, double percent) {
        projectedStocks = new ArrayList<>();
        Rate = percent;
        for (StockChild stock : list) {
            years = years;
            principal = stock.getTotWorth();
            //calculate value or return if none projection
            for (int i = 0; i <= years; i++) {
                worth = principal * Math.pow(1 + Rate, i);

            }
            //add projected worth
            stock.setProjWorth(worth);
            //return stock list with projected stock data changed
            projectedStocks.add(stock);
        }
        return projectedStocks;
    }

    //calculate number of years to sim
    public double calcYears(int inDays, int inMonths, int inYears) {
        double days = inDays;
        days += (inMonths * 30);
        days += (inYears * 365);
        double years = (days / 365);
        return years;

    }

    //reset data for a sim
    public void clear() {
        principal = 0;
        years = 0;
        worth = 0;
        projectedStocks.clear();
    }
}
