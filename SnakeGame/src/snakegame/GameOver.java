/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.FontMetrics;
import javax.swing.JFrame;
import static snakegame.Game.GAME_WIDTH;

/**
 *
 * @author Greg
 */
public class GameOver extends JFrame{
    GameOver()
    {
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);
        this.setSize(600, 600);
    }
}
