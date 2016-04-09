package FTPS;

import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/24/2016.
 */
public class AccountUpdater implements Updater {
    ArrayList<Updatable> updaters;
    public AccountUpdater(){
        updaters = new ArrayList<>();
    }
    @Override
    public void register(Updatable o) {
        updaters.add(o);
    }

    @Override
    public void unregister(Updatable o) {
        updaters.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Updatable o: updaters)
            o.update();
    }
}
