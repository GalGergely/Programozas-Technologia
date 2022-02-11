/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import javax.swing.JFrame;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Greg
 */
public class menuframe extends JFrame {
    JFrame frame;
    public menuframe() {
     frame=new JFrame();
    this.setSize(615, 640);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JTextField t1,t2;  
    t1=new JTextField("Player1");  
    t1.setBounds(100,100,215,30);  
    this.add(t1);
    t2=new JTextField("Player2");  
    t2.setBounds(100,140,215,30);  
    this.add(t2);
    //rgb color p1
    JTextField r1,g1,b1;  
    r1=new JTextField("0");  
    r1.setBounds(320,100,30,30);  
    this.add(r1);
    g1=new JTextField("255");  
    g1.setBounds(350,100,30,30);  
    this.add(g1);
    b1=new JTextField("255");  
    b1.setBounds(380,100,30,30);  
    this.add(b1);
    //rgb color p2
    JTextField r2,g2,b2;  
    r2=new JTextField("255");  
    r2.setBounds(320,140,30,30);  
    this.add(r2);
    g2=new JTextField("0");  
    g2.setBounds(350,140,30,30);  
    this.add(g2);
    b2=new JTextField("0");  
    b2.setBounds(380,140,30,30);  
    this.add(b2);
    //add button
    JButton b=new JButton("Start Game");
    b.setBounds(200,200,215,115);
    b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String p1=t1.getText();
            String p2=t2.getText();
            myColor c1= new myColor(Integer.parseInt(r1.getText()),Integer.parseInt(g1.getText()),Integer.parseInt(b1.getText()));
            myColor c2= new myColor(Integer.parseInt(r2.getText()),Integer.parseInt(g2.getText()),Integer.parseInt(b2.getText()));
             try {
                 frame = new GameFrame(p1, c1, p2, c2);
             } catch (SQLException ex) {
                 Logger.getLogger(menuframe.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menuframe.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
    });
    this.add(b);
    this.setLayout(null);
    this.setVisible(true);
    }
   
}
