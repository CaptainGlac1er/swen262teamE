package FTPS;

/**
 * Created by CaptainGlac1er on 3/31/2016.
 */
public class WatchListBackend extends PageBackend implements Updatable {
    PageUpdater pageUpdater;
    public WatchListBackend(PageUpdater updater){
        pageUpdater = updater;
        setGUI(new WatchListGUI(this, new WatchListUpdater()));
    }

    @Override
    public void update() {

    }
}
