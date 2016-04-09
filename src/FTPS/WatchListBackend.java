package FTPS;

/**
 * Created by CaptainGlac1er on 3/31/2016.
 */
public class WatchListBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    Portfolio portfolio;
    public WatchListBackend(PageUpdater updater, Portfolio portfolio){
        this.portfolio = portfolio;
        pageUpdater = updater;
        setGUI(new WatchListGUI(this, new WatchListUpdater(), portfolio));
    }

    @Override
    public void update() {
        getGUI().update();
    }
}
