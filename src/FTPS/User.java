package FTPS;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class User {
    private String username;
    private String password; //the hashed password

    public User(String username){
        this.username = username;
    }

    public portfolio exportPortfolio(){
        return null;
    }
    public void importPortolio(portfolio portfolio) {

    }
    public String getUsername(){
        return username;
    }
}
