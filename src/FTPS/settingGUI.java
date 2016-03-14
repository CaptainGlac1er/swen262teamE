package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class SettingGUI extends JFrame {
    Portfolio portfolio;

    /**
     *
     * @param portfolio
     */
    public SettingGUI(Portfolio portfolio) {
        this.portfolio = portfolio;
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton export = new JButton("Export Portfolio");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portfolio.exportPortfolio(true);
            }
        });
        this.add(export);
        this.setSize(300, 400);
        setVisible(true);
    }
}
