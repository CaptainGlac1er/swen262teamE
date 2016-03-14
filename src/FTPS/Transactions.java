package FTPS;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Transactions {
    //what will we do for storing time?
    private String transInfo;
    private String transType;
    private String dateTime;

    public Transactions(String type, String info){
        LocalDateTime timePoint = LocalDateTime.now();
        int month = timePoint.getMonthValue();
        int day = timePoint.getDayOfMonth();
        int year = timePoint.getYear();
        LocalTime timeTemp = timePoint.toLocalTime();
        String time = timeTemp.toString();
        dateTime = month + "/"+day+"/"+year+"  At:" + time;
        transInfo = info;
        transType = type;
    }
    public Transactions(String type, String info, String dateTime){
        transType = type;
        transInfo = info;
        this.dateTime = dateTime;
    }
    public String getType(){
        return transType;
    }
    public String getTime(){
        return dateTime;
    }
    public String getInfo(){
        return transInfo;
    }
}
