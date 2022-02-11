package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

public class Employee {
    private int    ID;
    private String Name;
    private int    Salary;
    private int    WorkedHours;
    private double Payment;

    public Employee(int ID, String Name, int Salary, int WorkedHours, double Payment) {
        this.ID = ID;
        this.Name = Name;
        this.Salary = Salary;
        this.WorkedHours = WorkedHours;
        this.Payment = Payment;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }   

    public int getWorkedHours() {
        return WorkedHours;
    }

    public double getPayment() {
        return Payment;
    }

    public boolean persist() {
        String cmd = "INSERT INTO Employee (Name,Salary) VALUES('" + Name + "', " + Salary + ");";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/salary?"
                    + "serverTimezone=UTC&user=student&password=asd123");
            try (Statement stmt = conn.createStatement()) {
                return stmt.execute(cmd);
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }         
        return false;
    }
}
