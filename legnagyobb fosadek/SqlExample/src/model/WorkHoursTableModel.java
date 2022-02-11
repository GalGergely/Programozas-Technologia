package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;

public class WorkHoursTableModel extends AbstractTableModel{
    private final ArrayList<DailyWork> workhours;   

    public WorkHoursTableModel() {
        workhours = new ArrayList<>();
    }
    
    public void LoadTable(int employee_id, int year, int month){
        workhours.clear();
        String query = "SELECT Day, Arrival, Leaving "
                + "FROM Workhours "
                + "WHERE FK_EmployeeID = " + employee_id + " AND YEAR(Day) = " + year + " AND MONTH(Day) = " + month + ";";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/salary?"
                    + "serverTimezone=UTC&user=student&password=asd123");
            try (Statement stmt = conn.createStatement()) {
                
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    Date day = rs.getDate(1);
                    Date arrival = rs.getTime(2);
                    Date exit = rs.getTime(3);
                    workhours.add(new DailyWork(day, arrival, exit));
                }
            }
            conn.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return workhours.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int i) {
        String colNames[] = new String[]{ "Dátum", "Érkezés", "Távozás" };
        return colNames[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return String.class;
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        if (column == 0) return workhours.get(row).getDay();
        if (column == 1) return workhours.get(row).getArrivalTime();
        if (column == 2) return workhours.get(row).getExitTime();
        return null;
    }
    
}
