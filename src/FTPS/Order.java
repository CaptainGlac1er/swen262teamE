package FTPS;

/**
 * Created by ajg72 on 3/10/2016.
 */
//Interface for Command
public interface Order {
    void execute();
    void undo();
}
