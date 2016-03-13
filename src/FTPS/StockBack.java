package FTPS;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/13/2016.
 */
public class StockBack {
    ArrayList<StockChild> child;
    StockGUI jframe;
    public StockBack(ArrayList<StockChild> child){
        this.child = child;
        jframe = new StockGUI();
    }
    public void addStocks(){

    }
}
