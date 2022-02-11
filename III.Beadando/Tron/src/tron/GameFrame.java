/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Greg
 */
public class GameFrame extends JFrame{

    GamePanel game;

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(null);
        this.setTitle("Tron by Neyoo");
        game = new GamePanel();
        this.add(game);
        this.setVisible(true);
        }
        

}