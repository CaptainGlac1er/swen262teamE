package FTPS;

import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public interface Updater {
    void register(Updatable o);
    void unregister(Updatable o);
    void notifyObserver();
}
