/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ourstd;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Greg
 */
public class Map {
    ArrayList<ArrayList<MapAtributes>> gameBoard;
    int RANGE_FOR_CASTLES = 3;

    public Map(int n) {
        gameBoard = new ArrayList();
        for (int i = 0; i < n; i++) {
            ArrayList<MapAtributes> tmp = new ArrayList();
            for (int j = 0; j < n; j++) {
                
                int tileRandom = random(0,100);
                if (tileRandom < 60) {
                    Land land = new Land();
                    tmp.add(land);
                }
                if (tileRandom < 80 && tileRandom >= 60) {
                    Mountain mountain = new Mountain();
                    tmp.add(mountain);
                }
                if (tileRandom >= 80) {
                    Water water = new Water();
                    tmp.add(water);
                }
            }
         gameBoard.add(tmp);
        }
        int x1 = random(1,RANGE_FOR_CASTLES);
        int y1 = random(1,RANGE_FOR_CASTLES);
        int x2 = random(n-RANGE_FOR_CASTLES,n-1);
        int y2 = random(n-RANGE_FOR_CASTLES,n-1);
        
        //generate castle 1
        ArrayList <MapAtributes> temperary = gameBoard.get(x1);
        Castle castle1 = new Castle();
        temperary.set(y1, castle1);
        gameBoard.set(x1, temperary);
        
        //generate castle 2
        temperary = gameBoard.get(x2);
        Castle castle2 = new Castle();
        temperary.set(y2, castle2);
        gameBoard.set(x2, temperary);
        
        //kitakaritas a castle korul
        //melette levo teruletek
        temperary = gameBoard.get(x1);
        temperary.set(y1-1, new Land());
        temperary.set(y1+1, new Land());
        gameBoard.set(x1, temperary);
        //alatta levo teruletek
        temperary = gameBoard.get(x1-1);
        temperary.set(y1, new Land());
        gameBoard.set(x1-1, temperary);
        //felette levo teruletek
        temperary = gameBoard.get(x1+1);
        temperary.set(y1, new Land());
        gameBoard.set(x1+1, temperary);
        
    }
    
    int random(int low, int high) {
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return result;
    }
    void logMap() {
        for (int i = 0; i < gameBoard.size(); i++) {
            for (int j = 0; j < gameBoard.size(); j++) {
                gameBoard.get(i).get(j).consoleLog();
            }
            System.out.printf("\n");
        }
    }
    
    
}
