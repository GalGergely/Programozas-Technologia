package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class SalaryTableModel extends AbstractTableModel{
    private Date currentMonth;
    private final ArrayList<Employee> employees;
    
    public SalaryTableModel(){
        employees = new ArrayList<>();
        LoadTable(2018, 2);
    }

    public void LoadTable(int year, int month){
        employees.clear();
        currentMonth = SalaryCalculator.YearMonthToDate(year, month - 1);
        int desiredWorkHours = SalaryCalculator.NumberOfDesiredWorkHours(currentMonth);
        
        // Azok, akik dolgoztak az aktuális hónapban
        String query1 = "SELECT "
                + "id, name, salary, (SUM(TIME_TO_SEC(leaving) - TIME_TO_SEC(arrival)) DIV 60) AS minutes "
                + "FROM employee, workhours "
                + "WHERE id = FK_employeeID AND MONTH(day) = " + month + " AND YEAR(day) = " + year + " "
                + "GROUP BY id;";

        // Azok, akik nem dolgoztak az aktuális hónapban
        String query2 = "SELECT "
                + "id, name, salary "
                + "FROM employee "
                + "WHERE id NOT IN "
                + "(SELECT FK_employeeID "
                + "FROM workhours "
                + "WHERE id = FK_employeeID AND MONTH(day) = " + month + " AND YEAR(day) = " + year + ");";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/salary?"
                    + "serverTimezone=UTC&user=student&password=asd123");
            try (Statement stmt = conn.createStatement()) {
                
                ResultSet rs = stmt.executeQuery(query1);
                while (rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int salary = rs.getInt(3);
                    int workedhours = (int)Math.floor(rs.getInt(4) / 60.0);
                    double payment = SalaryCalculator.CalculateMonthlyPayment(salary, workedhours, desiredWorkHours);
                    
                    employees.add(new Employee(id, name, salary, workedhours, payment));
                }
                
                rs = stmt.executeQuery(query2);
                while (rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    int salary = rs.getInt(3);
                    employees.add(new Employee(id, name, salary, 0, 0));
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
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int i) {
        String colNames[] = new String[]{ "Név", "Bér", "Fizetés", "Munkaórák" };
        return colNames[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return (i == 0) ? String.class : Integer.class;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
    public int getID(int row){
        return employees.get(row).getID();
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column){
            case 0: return employees.get(row).getName();
            case 1: return employees.get(row).getSalary();
            case 2: return employees.get(row).getPayment();
            case 3: return employees.get(row).getWorkedHours();                    
            default: return null;
        }        
    }

    @Override
    public void setValueAt(Object o, int row, int column) {}
    
    
}
