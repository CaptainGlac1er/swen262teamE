package FTPS;

import javax.swing.*;
import java.io.*;
import java.lang.*;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class portfolio {
    User user;
    public portfolio(User user){
        this.user = user;
        loadPortfolio();
        new portfolioGUI(user);
    }
    public void createAsset(){

    }
    public void getPortfolio(){

    }
    public void getUser(String username){

    }
    public boolean loadPortfolio(){
        JFileChooser f = new JFileChooser();
        File dir = new File(f.getCurrentDirectory().toString().concat("\\Users"));
        java.lang.System.out.println(dir.getAbsolutePath());
        dir.mkdir();
        File checkfile = new File(dir, user.getUsername() + "Port.txt");
        if (!checkfile.exists()) {
            try {
                PrintWriter writer = new PrintWriter(checkfile, "UTF-8");
                writer.println("User Info");
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

            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
