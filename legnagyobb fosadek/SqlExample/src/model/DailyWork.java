package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyWork {
    private String day, arrivalTime, exitTime;

    public DailyWork(Date day, Date arrivalTime, Date exitTime) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd.");
        
        cal.setTime(day);
        this.day = dateFormat.format(cal.getTime());
       
        dateFormat = new SimpleDateFormat("HH:mm");

        cal.setTime(arrivalTime);
        this.arrivalTime = dateFormat.format(cal.getTime());
        
        cal.setTime(exitTime);
        this.exitTime = dateFormat.format(cal.getTime());
    }
    
    public String getDay(){
        return day;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getExitTime() {
        return exitTime;
    }
}
