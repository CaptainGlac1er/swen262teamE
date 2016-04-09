package FTPS;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by CaptainGlac1er on 3/26/2016.
 */
public class AssetsUpdater implements Updater {
    ArrayList<Updatable> updatableStocks = new ArrayList<>();
    @Override
    public void register(Updatable o) {
        updatableStocks.add(o);
    }

    @Override
    public void unregister(Updatable o) {
        updatableStocks.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Updatable o: updatableStocks)
            o.update();
    }
}
