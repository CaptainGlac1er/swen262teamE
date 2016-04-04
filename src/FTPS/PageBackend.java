package FTPS;

/**
 * Created by George Walter Colrove IV on 3/22/2016.
 */
public abstract class PageBackend implements Updatable{
    private PageGUI gui;
    public PageGUI getPage(){
        return gui;
    }
    public void setGUI(PageGUI GUI){
        gui = GUI;
        gui.panel.revalidate();
        gui.panel.repaint();
        System.out.println(gui);
    }
    public PageGUI getGUI(){
        System.out.println(gui);
        return gui;
    }
}
