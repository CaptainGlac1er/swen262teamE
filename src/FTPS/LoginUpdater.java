package FTPS;

import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/23/2016.
 */
public class LoginUpdater implements Updater {
    ArrayList<Updatable> updatables = new ArrayList<>();
    String username = "";
    String password = "";
    public LoginUpdater(){
    }
    @Override
    public void register(Updatable o) {
        updatables.add(o);
    }

    @Override
    public void unregister(Updatable o) {
        updatables.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Updatable u: updatables)
            ((LoginBack)u).update(username,password);
    }
    public void updateUsername(String username){
        this.username = username;
        notifyObserver();
    }
    public void updatePassword(String password){
        this.password = password;
        notifyObserver();
    }
}
