package FTPS;

/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class User {
    private String username;
    private String password; //the hashed password
    private Portfolio port;

    /**
     * @param username
     */
    public User(String username) {
        this.username = username;
    }

    /**
     * @return
     */
    public Portfolio exportPortfolio() {
        return null;
    }

    /**
     * @param portfolio
     */
    public void importPortolio(Portfolio portfolio) {

    }

    /**
     * @return
     */
    public String getUsername() {
        return username;
    }


}