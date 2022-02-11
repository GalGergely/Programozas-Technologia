package model;

import java.util.Date;
import java.util.Calendar;

public class SalaryCalculator {
    public static int NumberOfWeekdays(Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int days = 0;
        int last_day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= last_day; i++){
            cal.set(Calendar.DAY_OF_MONTH, i);
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (day < 6) days++;
        }
        return days;
    }
        
    public static Date YearMonthToDate(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        return cal.getTime();
    }
    
    public static int NumberOfDesiredWorkHours(Date d){
        return NumberOfWeekdays(d) * 8;
    }
    
    public static int NumberOfDesiredWorkHours(int year, int month){
        return NumberOfWeekdays(YearMonthToDate(year, month)) * 8;
    }
    
    
    public static double CalculateMonthlyPayment(int Salary, int WorkedHours, int DesiredHours){
        if (WorkedHours < DesiredHours){
            return Salary * ((double)WorkedHours / DesiredHours);
        }
        int overtime = WorkedHours - DesiredHours;
        return Salary + (1.2 * Salary * overtime) / DesiredHours;    
    }
}
