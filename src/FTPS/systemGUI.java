package FTPS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class systemGUI extends JFrame implements ActionListener{
    System system;
    JTextField userText; //username used later
    JTextField passwordText; //password used later

    public systemGUI(System system){
        this.system = system;
        JFrame frame = new JFrame("Demo application");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        this.placeComponents(panel);

        frame.setVisible(true);
    }

    public void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        userText.addActionListener(this);
        passwordText.addActionListener(this);

    }



    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();
        String password = passwordText.getText();
        String user = userText.getText();
        switch(source.getText()){
            case "login":
                system.LoginAction(user, password);
                break;
            case "register":
                system.RegisterAction(user, password);
                break;
        }
    }


}
