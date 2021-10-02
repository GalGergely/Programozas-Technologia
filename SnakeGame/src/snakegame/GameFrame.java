/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    Game game;
    JButton resetButton;
    JButton toplist;

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(null);
        this.setTitle("Snake Game by Neyoo");
        game = new Game();
        this.add(game);
        this.setVisible(true);
        

    }
}
