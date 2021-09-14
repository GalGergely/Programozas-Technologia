/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener {

    Game game;
    JButton resetButton;

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(715, 640);
        this.setLayout(null);
        this.setTitle("Snake Game by Neyoo");
        resetButton = new JButton();
        resetButton.setText("Reset");
        resetButton.setSize(100, 50);
        resetButton.setFocusable(false);
        resetButton.setLocation(0, 200);
        resetButton.addActionListener(this);
        game = new Game();
        this.add(resetButton);
        this.add(game);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            this.remove(game);
            game = new Game();
            this.add(game);
        }
    }
}
