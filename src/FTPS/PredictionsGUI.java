package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by CaptainGlac1er on 3/14/2016.
 */
public class PredictionsGUI extends JFrame {
    JFrame frame = this;
    PortEngine portEngine;
    JPanel info;
    Portfolio portfolio;

    public PredictionsGUI(PortEngine portEngine, Portfolio port) {
        portfolio = port;
        JLabel label = new JLabel("Predictions");
        frame.add(label, BorderLayout.NORTH);
        this.portEngine = portEngine;
        makeGUI();
        frame.setSize(300, 400);
        frame.setVisible(true);
    }

    private void makeGUI() {
        info = new JPanel();
        info.setLayout(new GridLayout(6, 2));
        JLabel dayLabel = new JLabel("Days");
        JTextField day = new JTextField("0");
        JLabel monthLabel = new JLabel("Months");
        JTextField month = new JTextField("0");
        JLabel yearLabel = new JLabel("Years");
        JTextField year = new JTextField("0");
        JLabel rateLabel = new JLabel("Rate Percentage");
        JTextField rate = new JTextField("0");
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double rateAmount = Double.parseDouble(rate.getText()) / 100;
                ArrayList<StockChild> output;
                try {
                    output = portEngine.simBullBearNone(portfolio.getAssets().GetStocks(), portEngine.calcYears(Integer.parseInt(day.getText()), Integer.parseInt(month.getText()), Integer.parseInt(year.getText())), rateAmount);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Could not parse a field. Please check input");
                    return;
                }
                double out = 0;

                for (StockChild s : output) {
                    out += s.getProjWorth();
                }
                out = ((int) (out * 100)) / 100.0;
                String addToOutput = "";
                if (rateAmount > 0)
                    addToOutput = "bull market";
                else if (rateAmount < 0)
                    addToOutput = "bear market";
                else
                    addToOutput = "stale market";
                JOptionPane.showMessageDialog(null, "With the rate of " + rateAmount + " in a " + addToOutput + " the expected value of the stocks would be $" + out);
                portEngine.clear();
            }
        });
        info.add(dayLabel);
        info.add(day);
        info.add(monthLabel);
        info.add(month);
        info.add(yearLabel);
        info.add(year);
        info.add(rateLabel);
        info.add(rate);
        info.add(submit);
        frame.add(info, BorderLayout.CENTER);


    }
}
