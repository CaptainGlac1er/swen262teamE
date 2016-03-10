package FTPS;

import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class portfolio {
    User user;
    Assets assets = new Assets();
    PortEngine portEngine;
    public portfolio(User user){
        this.portEngine = new PortEngine();
        this.user = user;
        loadPortfolio();
        new portfolioGUI(user, this);
    }
    public Assets getAssets(){
        return assets;
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
                writer.println("1");
                writer.println("1");
                writer.println("a");
                writer.println("0");
                writer.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String currentLine;
            //BufferedReader br = new BufferedReader(new FileReader(checkfile));
            Scanner scanner = new Scanner(checkfile);
            int topLevelEntries = scanner.nextInt();
            for(int a = 0; a < topLevelEntries; a++){
                int amount = scanner.nextInt();
                for(int b = 0; b < amount; b++) {
                    char type = scanner.next().charAt(0);
                    switch (type) {
                        case 'a':
                            String accountName = scanner.nextLine();
                            double balance = scanner.nextDouble();
                            assets.AddCashAccount(balance, accountName);
                            break;
                        case 's':
                            String ticker = scanner.nextLine();
                            int count = scanner.nextInt();
                            assets.AddStock(count,portEngine.search(ticker));
                            break;
                    }
                }

            }
            /*while((currentLine = br.readLine()) != null){
                switch (currentLine){
                    case "a":
                        for()
                        break;
                    case "s":
                        break;
                }
            }*/
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e){
            e.printStackTrace();
        }
        return true;
    }
}
