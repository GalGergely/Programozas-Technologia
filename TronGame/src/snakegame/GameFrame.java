/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GameFrame extends JFrame{

    Game game;
    JButton resetButton;
    JFrame frame;
    

    GameFrame(String p1, myColor c1, String p2, myColor c2) throws SQLException, ClassNotFoundException {
        frame=new JFrame();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.setSize(615, 640);
        this.setSize(615, 700);
        this.setLayout(null);
        this.setTitle("Tron Game by Neyoo");
        game = new Game(p1,  c1,  p2,  c2);
        this.add(game);
        JButton button=new JButton("Show me the leederboard");
        button.setBounds(150,615,300,50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HighScoreWindow hm = new HighScoreWindow(game.db.getScores(), frame);
                } catch (SQLException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         });
        this.add(button);
        this.setVisible(true);
    }
}
