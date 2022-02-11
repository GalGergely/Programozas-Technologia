/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

/**
 *
 * @author Greg
 */
public class Player {
    String name;
    int x[] = new int[14400];
    int y[] = new int[14400];
    int bodyParts = 100;
    char direction = 'R';
    myColor color;

    public Player(String name, myColor color, int x, int y, char direction) {
        this.name=name;
        this.color=color;
        this.x[0]=x;
        this.y[0]=y;
        this.direction=direction;
    }

}

