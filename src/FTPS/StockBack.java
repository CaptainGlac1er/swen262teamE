package FTPS;

import javax.sound.sampled.Port;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/13/2016.
 */
public class StockBack {
    public StockGUI jframe;
    public Portfolio portfolio;
    public StockBack(Portfolio p){
        portfolio = p;
        jframe = new StockGUI(this);
        addStocks();
    }
    public void addStocks(){
        jframe.clear();
        for(StockChild s: portfolio.getAssets().GetStocks())
            jframe.addStockRow(s);
    }
}
