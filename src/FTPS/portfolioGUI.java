package FTPS;

import javax.swing.*;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class portfolioGUI extends JFrame {
    User user;
    public portfolioGUI(User user){
        this.user = user;
    }
    public portfolioGUI(){
        JLabel testLabel = new JLabel("Test User");
        this.add(testLabel);
        this.setSize(300,400);
        setVisible(true);
    }

}
