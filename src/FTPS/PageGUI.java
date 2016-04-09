package FTPS;

import javax.swing.*;

/**
 * Created by George Walter Colrove IV on 3/22/2016.
 */
public abstract class PageGUI implements Updatable {
    protected String pageTitle = "";
    protected JPanel panel = new JPanel();
    public JPanel getPage(){
        return panel;
    }
    public String getPageTitle(){ return pageTitle; }
    public abstract void addComponents();
    public void clear(){
        panel.removeAll();
    }

}
