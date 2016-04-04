package FTPS;

import javax.swing.*;
import java.awt.*;

public class FTPS extends JFrame implements Updatable {
    private PageBackend currentBackend;
    private JPanel currentPage = new JPanel();
    public static void main(String[] args) {
        new FTPS();
    }
    private PageUpdater pageHandler = new PageUpdater();
    public FTPS(){
        new LoginBack(pageHandler);
        this.setSize(300, 300);
        this.setVisible(true);
        this.add(currentPage);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pageHandler.register(this);
        StockDB.getInstance().start();
    }
    @Override
    public void update() {

    }
    public void update(PageGUI newPage, PageBackend newBackend){
        currentBackend = newBackend;
        currentPage.removeAll();
        this.setTitle(newPage.getPageTitle());
        System.out.println("Setting page to " + newPage.getClass() + " which has " + newPage.getPage().getComponents().length + " components");
        ((new JFrame()).add(newPage.getPage())).setVisible(true);
        currentPage.add(newPage.getPage(), BorderLayout.CENTER);
        currentPage.revalidate();
        currentPage.repaint();
        this.pack();
        System.out.println("Thia page has " + this.getComponents().length + " components");
    }
}
