package FTPS;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class System{
    JTextField userText; //username used later
    JTextField passwordText; //password used later
    private static HashMap<String, Integer> userStorage; //storage for userdata
    public static ArrayList<StockChild> AllStocks = new ArrayList<>();
    /**
     * intiliazes user hashmap (calls LoadUsers) and the GUI
     *
     */
    public System(){
        //System fsystem = new System();
        new SystemGUI(this);
        userStorage = new HashMap<>();
        LoadUsers();
        getAllStocks();

    }

    /**
     * Loads text file and puts them inro user info hashmap
     */
    public static void LoadUsers(){
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

    /**
     * Checks if user info is correct and logs them in if it is
     * else it gives an alert that the information was incorrect
     * @param username
     * @param password
     */
    public void LoginAction(String username, String password){

        if (PasswordCheck(username, password)){
            JFileChooser f = new JFileChooser();
            java.lang.System.out.println("logged in " + username + " " + f.getCurrentDirectory().toString());
            User user = new User(username);
            user.openPortfolio();
            //load user portfolio
            //new portfolio();
        }
        else{
            java.lang.System.out.println("error logging in" + username);
            //incorrect password or username alert
        }


    }

    /**
     * Writes the user info into text file if it is new
     * @param username
     * @param password
     */
    public void RegisterAction(String username, String password) {
        JFileChooser f = new JFileChooser();
        File checkfile = new File(f.getCurrentDirectory().toString().concat("/UserInfo.txt"));
        //if (!checkfile.exists()) {
        //    try {
        //        PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
        //        writer.println("Username,Password");
        //        writer.close();
//
        //    } catch (FileNotFoundException e) {
        //        e.printStackTrace();
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}

        if (userStorage.isEmpty() || !userStorage.containsKey(username)) {
            userStorage.put(username, Hasher(password));
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(checkfile, true)))) {
                out.println(username + "," + Hasher(password));
            }catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            //User already exists alert GUI
            java.lang.System.out.print(("user already exists"));
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

    public static ArrayList<StockChild> getAllStocks(){
        String filepath = java.lang.System.getProperty("user.dir") + "\\src\\equities.csv";
        try {
            String currentLine;
            BufferedReader br = new BufferedReader(new FileReader(filepath));

            while((currentLine = br.readLine()) != null) {
                java.lang.System.out.println(currentLine);
                String[] info = currentLine.split("\"");
                java.lang.System.out.println(info[5]);
                StockChild currentStock = new StockChild(info[3], info[1], info[7], 0, Double.parseDouble(info[5]));
                AllStocks.add(currentStock);
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e){
            e.printStackTrace();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return AllStocks;
    }

}
