package FTPS;

import javax.swing.*;
import java.io.*;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class User {
    private String username;
    private String password; //the hashed password
    private Portfolio port;

    public User(String username){
        this.username = username;
    }

    public Portfolio exportPortfolio(){
        return null;
    }
    public void importPortolio(Portfolio portfolio) {

    }
    public String getUsername(){
        return username;
    }
    public boolean openPortfolio(){
        new Portfolio(this);
        return true;
    }

}
