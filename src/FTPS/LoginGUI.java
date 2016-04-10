package FTPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by George Walter Colrove IV on 3/22/2016.
 */
public class LoginGUI extends PageGUI {
    LoginBack backend;
    LoginUpdater updater;
    public LoginGUI(LoginBack backend, LoginUpdater updater){
        this.updater = updater;
        this.backend = backend;
        pageTitle = "Login";
        addComponents();
        updater.register(backend);
    }

    @Override
    public void addComponents() {
        panel.setLayout(new FlowLayout());
        //panel.setMaximumSize(new Dimension(300, 300));
        panel.setPreferredSize(new Dimension(300, 150));
        JPanel fields = new JPanel(new GridLayout(2,2));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        fields.add(usernameLabel);
        fields.add(passwordLabel);
        fields.add(username);
        fields.add(password);
        fields.setPreferredSize(new Dimension(250, 50));
        panel.add(fields);
        JPanel buttons = new JPanel(new GridLayout(1,2));
        JButton login = new JButton("Login");
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updater.updateUsername(username.getText());
                updater.updatePassword(password.getText());
                backend.login();
            }
        });
        JButton register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updater.updateUsername(username.getText());
                updater.updatePassword(password.getText());
                backend.registerUser();
            }
        });
        buttons.add(login);
        buttons.add(register);
        buttons.setPreferredSize(new Dimension(250, 50));
        panel.add(buttons);

    }

    @Override
    public void update() {

    }
}
