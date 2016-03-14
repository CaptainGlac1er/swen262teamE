package FTPS;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CaptainGlac1er on 3/4/2016.
 */
public class FTPS {
    public static ArrayList<StockChild> AllStocks = new ArrayList<>();
    private static HashMap<String, Integer> userStorage; //storage for userdata
    JTextField userText; //username used later
    JTextField passwordText; //password used later

    /**
     * intiliazes user hashmap (calls LoadUsers) and the GUI
     */
    public FTPS() {
        //System fsystem = new System();
        new SystemGUI(this);
        userStorage = new HashMap<>();
        LoadUsers();
        getAllStocks();

    }

    public static void main(String[] args) {
        new FTPS();
    }

    /**
     * Loads text file and puts them inro user info hashmap
     */
    public static void LoadUsers() {
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
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(checkfile));

            while ((currentLine = br.readLine()) != null) {
                if ((currentLine.equals("Username,Password"))) {
                    continue;
                }
                String[] info = currentLine.split(",");
                userStorage.put(info[0], Integer.parseInt(info[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return
     */
    public static ArrayList<StockChild> getAllStocks() {
        String filepath = java.lang.System.getProperty("user.dir") + "\\src\\equities.csv";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            while ((currentLine = br.readLine()) != null) {
                java.lang.System.out.println(currentLine);
                String[] info = currentLine.split("\"");
                java.lang.System.out.println(info[5]);
                StockChild currentStock = new StockChild(info[3], info[1], info[7], 0, Double.parseDouble(info[5]));
                AllStocks.add(currentStock);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return AllStocks;
    }

    /**
     * Checks if user info is correct and logs them in if it is
     * else it gives an alert that the information was incorrect
     *
     * @param username
     * @param password
     */
    public boolean LoginAction(String username, String password) {

        if (PasswordCheck(username, password)) {
            JFileChooser f = new JFileChooser();
            java.lang.System.out.println("logged in " + username + " " + f.getCurrentDirectory().toString());
            User user = new User(username);
            user.openPortfolio();
            return true;
            //load user portfolio
            //new portfolio();
        } else {
            //incorrect password or username alert
            JOptionPane.showMessageDialog(null, "Error Logging in " + username);
            return false;
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
