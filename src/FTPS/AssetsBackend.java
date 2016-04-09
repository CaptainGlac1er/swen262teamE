package FTPS;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/26/2016.
 */
public class AssetsBackend extends PageBackend {
    PageUpdater pageUpdater;
    Portfolio portfolio;
    AssetsUpdater assetsUpdater;
    public AssetsBackend(PageUpdater pageUpdater, Portfolio portfolio){
        this.portfolio = portfolio;
        this.pageUpdater = pageUpdater;
        assetsUpdater = new AssetsUpdater();
        setGUI(new AssetsGUI(this, assetsUpdater, portfolio));

    }
    @Override
    public void update() {
        ((AssetsGUI)getGUI()).update();
        assetsUpdater.notifyObserver();
    }
    public void addStocks(){
    }
    public ArrayList<StockChild> getStocks(){
        return portfolio.getAssets().GetStocks();
    }
    public Portfolio getPortfolio(){
        return portfolio;
    }

}
