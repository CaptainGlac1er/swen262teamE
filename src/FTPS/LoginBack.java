package FTPS;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

/**
 * Created by George Walter Colrove IV on 3/22/2016.
 */
public class LoginBack extends PageBackend implements Updatable {
    private static HashMap<String, Integer> userStorage = new HashMap<>(); //storage for userdata
    String enteredUsername = "";
    String enteredPassword = "";
    PageUpdater pageUpdater;
    public LoginBack(PageUpdater pageUpdater){
        LoadUsers();
        setGUI(new LoginGUI(this, new LoginUpdater()));
        this.pageUpdater = pageUpdater;
        pageUpdater.setCurrentPageAndBackend(getGUI(), this);
        //registerUser("George", "password");
    }
    public boolean registerUser(){
        String hash = Security.hashString(enteredPassword, 2343);
        System.out.println(enteredUsername + " " + hash);
        RegisterAction(enteredUsername, enteredPassword);
        return true;
    }

    @Override
    public void update() {

    }
    public void update(String username, String password){
        enteredPassword = password;
        enteredUsername = username;
    }
    public void login(){
        System.out.println(enteredUsername + " " + enteredPassword);
        User loginUser;
        if((loginUser = LoginAction(enteredUsername, enteredPassword)) != null)
            new PortfolioBackend(pageUpdater, loginUser);
    }
    public static void LoadUsers() {
        JFileChooser f = new JFileChooser();
        File checkfile = new File(f.getCurrentDirectory().toString().concat("/UserInfo.txt"));
        try {

            if (!checkfile.exists()) {
                PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
                writer.println("Username,Password");
                writer.close();
            }

            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(checkfile));

            while ((currentLine = br.readLine()) != null) {
                if ((currentLine.equals("Username,Password"))) {
                    continue;
                }
                String[] info = currentLine.split(",");
                userStorage.put(info[0], Integer.parseInt(info[1]));
            }
        } catch(IOException e){
            System.out.println("Load User error:\n");
            e.printStackTrace();

        }
    }

    /**
     * Checks if user info is correct and logs them in if it is
     * else it gives an alert that the information was incorrect
     *
     * @param username
     * @param password
     */
    public User LoginAction(String username, String password) {

        if (PasswordCheck(username, password)) {
            //java.lang.System.out.println("logged in " + username + " " + f.getCurrentDirectory().toString());
            User user = new User(username);
            return user;
            //load user portfolio
            //new portfolio();
        } else {
            //incorrect password or username alert
            JOptionPane.showMessageDialog(null, "Error Logging in " + username);
            return null;
        }


    }

    /**
     * Writes the user info into text file if it is new
     *
     * @param username
     * @param password
     */
    public boolean RegisterAction(String username, String password) {
        JFileChooser f = new JFileChooser();
        File checkfile = new File(f.getCurrentDirectory().toString().concat("/UserInfo.txt"));

        if (userStorage.isEmpty() || !userStorage.containsKey(username)) {
            userStorage.put(username, Hasher(password));
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(checkfile, true)))) {
                out.println(username + "," + Hasher(password));
            } catch (IOException e) {
                e.printStackTrace();
            }
            LoginAction(username, password);
            return true;

        } else {
            //User already exists alert GUI
            JOptionPane.showMessageDialog(null, "User already in system");
            return false;
        }

    }

    /**
     * @param password
     * @return
     */
    public int Hasher(String password) {
        int i = 0;
        int hashedvalue = 0;
        while (i < password.length()) {
            hashedvalue += (password.charAt(i) * 31);
            i++;
        }
        return hashedvalue;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    public Boolean PasswordCheck(String username, String password) {
        if (!userStorage.isEmpty()) {
            if (userStorage.get(username) != null && Hasher(password) == (userStorage.get(username))) {
                return true;
            }
        }
        return false;
    }
}
