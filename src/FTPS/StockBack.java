package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/13/2016.
 */
public class StockBack {
    StockGUI jframe;
    public StockBack(Portfolio p){
        jframe = new StockGUI();
        addStocks(p);
    }
    public void addStocks(Portfolio p){
        for(StockChild s: p.getAssets().GetStocks())
            jframe.addStockRow(s);
    }
}
