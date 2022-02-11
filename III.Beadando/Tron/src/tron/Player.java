/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tron;

/**
 *
 * @author Greg
 */
public class Player {
    char direction = 'R';
    int x[];
    int y[];
    int playerLength = 5;

    public Player(int GAME_UNITS) {
        int x[] = new int[GAME_UNITS];
        int y[] = new int[GAME_UNITS];
    }
}
