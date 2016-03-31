package FTPS;

/**
 * Created by CaptainGlac1er on 3/13/2016.
 */
public class StockBack {
    public StockGUI jframe;
    public Portfolio portfolio;

    /**
     * Create backend for stockGUI
     * @param p portfolio to use to get data from
     */
    public StockBack(Portfolio p) {
        portfolio = p;
        jframe = new StockGUI(this);
        addStocks();
    }

    /**
     * adds the stocks rows to the jframe
     */
    public void addStocks() {
        jframe.clear();
        for (StockChild s : portfolio.getAssets().GetStocks())
            jframe.addStockRow(s);
    }


}
