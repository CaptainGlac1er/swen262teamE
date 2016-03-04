package FTPS;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * Created by CaptainGlac1er on 2/28/2016.
 */
public class Transactions {
    //what will we do for storing time?
    private String transInfo;
    private String dateTime;
    private int day;
    private int month;
    private int year;
    private String time;

    Transactions(String type){
        LocalDateTime timePoint = LocalDateTime.now();
        month = timePoint.getMonthValue();
        day = timePoint.getDayOfMonth();
        year = timePoint.getYear();
        LocalTime timeTemp = timePoint.toLocalTime();
        time = timeTemp.toString();
        dateTime = month + "/"+day+"/"+year+"  At:" + time;
        transInfo = type;
    }
    public String getTime(){
        String outTime = dateTime;
        return outTime;
    }
    public String getInfo(){
        String outInfo = transInfo;
        return outInfo;
    }
}
