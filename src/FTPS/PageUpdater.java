package FTPS;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public class PageUpdater implements Updater {
    ArrayList<Updatable> updatables = new ArrayList<>();
    PageGUI currentPage;
    PageBackend currentBackend;
    public PageUpdater(){
    }
    @Override
    public void register(Updatable o) {
        updatables.add(o);
        notifyObserver();
    }

    @Override
    public void unregister(Updatable o) {
        updatables.add(o);
        notifyObserver();
    }

    @Override
    public void notifyObserver() {
        for(Updatable u: updatables){
            ((FTPS) u).update(currentPage, currentBackend);
        }
    }
    public void setCurrentPageAndBackend(PageGUI newPanel, PageBackend newBackend){
        currentPage = newPanel;
        currentBackend = newBackend;
        notifyObserver();
    }
}
