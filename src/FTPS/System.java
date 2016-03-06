package FTPS;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class System extends JFrame implements ActionListener{
    JTextField userText; //username used later
    JTextField passwordText; //password used later
    private static HashMap<String, Integer> userStorage; //storage for userdata

    public System(){
        //System fsystem = new System();
        JFrame frame = new JFrame("Demo application");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        this.placeComponents(panel);

        frame.setVisible(true);
        userStorage = new HashMap<>();
        //LoadUsers();

    }

    public static void LoadUsers(){
        JFileChooser f = new JFileChooser();
        File checkfile = new File(f.getCurrentDirectory().toString().concat("/UserInfo.txt"));
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(checkfile));

            while((currentLine = br.readLine()) != null){
                if((currentLine.equals("Username,Password"))){
                    continue;
                }
                String[] info = currentLine.split(",");
                userStorage.put(info[0], Integer.parseInt(info[1]));
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                LoginAction(user, password);
                break;
            case "register":
                RegisterAction(user, password);
                break;
        }
    }


    public void LoginAction(String username, String password){
        new portfolio();
        if (PasswordCheck(username, password)){
            //load user portfolio
        }



    }
    public void RegisterAction(String username, String password) {
        JFileChooser f = new JFileChooser();
        File checkfile = new File(f.getCurrentDirectory().toString().concat("/UserInfo.txt"));
        if (!checkfile.exists()) {
            try {
                PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
                writer.println("Username,Password");
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (userStorage.isEmpty() || !userStorage.containsKey(username)) {
            userStorage.put(username, Hasher(password));
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(checkfile, true)))) {
                out.println(username + "," + Hasher(password));
            }catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            //User already exists alert GUI
        }

    }

    public int Hasher(String password) {
        int i = 0;
        int hashedvalue = 0;
        while (i < password.length()) {
            hashedvalue+=(password.charAt(i)*31);
            i++;
        }
        return hashedvalue;
    }

    public Boolean PasswordCheck(String username, String password){
        if (!userStorage.isEmpty()){
            if (Hasher(password)==(userStorage.get(username))){
                return true;
            }
        }
        return false;
    }

}
