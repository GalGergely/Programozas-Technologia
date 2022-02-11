/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 *
 * @author pbali
 */
public class Database {

    private final String tableName = "snake";
    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;

    public Database(int maxScores) throws SQLException,ClassNotFoundException{
        this.maxScores = maxScores;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/snake?"
                + "serverTimezone=UTC&user=gergo&password=asdasd123");

        String insertQuery = "INSERT INTO SNAKE ( NAME , SCORE) VALUES (? ,?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM SNAKE WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    public ArrayList<HighScore> getScores() throws SQLException {
        String query = "SELECT * FROM SNAKE";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int length = results.getInt("SCORE");
            highScores.add(new HighScore(name, length));
        }
        sortHighScores(highScores);
        return highScores;
    }

    public void putHighScore(String name, int length) throws SQLException {
        ArrayList<HighScore> highScores = getScores();
        if (highScores.size() < maxScores) {
            insertScore(name, length);
        } else {
            int leastScore = highScores.get(highScores.size() - 1).getscore();
            if (leastScore < length) {
                deleteScores(leastScore);
                insertScore(name, length);
            }
        }
    }

    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                return t1.getscore() - t.getscore();
            }
        });
    }

    private void insertScore(String name, int length) throws SQLException {
        insertStatement.setString(1, name);
        insertStatement.setInt(2, length);
        insertStatement.executeUpdate();
    }

    private void deleteScores(int length) throws SQLException {
        deleteStatement.setInt(1, length);
        deleteStatement.executeUpdate();
    }
}
