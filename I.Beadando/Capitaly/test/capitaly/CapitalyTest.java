/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Greg
 */
public class CapitalyTest {
      
    @Test //1
    public void testRoundTest1() {
        Capitaly game = new Capitaly();
        try {
            game.read("data1.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        ArrayList<Integer> dataList = game.roundTest();
        assertEquals(dataList.get(0), (Integer)1);
        assertEquals(dataList.get(1), (Integer)8500);
        assertEquals(dataList.get(2), (Integer)2);
        assertEquals(dataList.get(3), (Integer)10500);
        assertEquals(dataList.get(4), (Integer)6);
        assertEquals(dataList.get(5), (Integer)8500);
    }
    @Test //2
    public void testRoundTest2() {
        Capitaly game = new Capitaly();
        try {
            game.read("data2.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        ArrayList<Integer> dataList = game.roundTest();
        assertEquals(dataList.get(0), (Integer)1);
        assertEquals(dataList.get(1), (Integer)8500);
        assertEquals(dataList.get(2), (Integer)2);
        assertEquals(dataList.get(3), (Integer)9000);
        assertEquals(dataList.get(4), (Integer)6);
        assertEquals(dataList.get(5), (Integer)9500);
    }
     @Test //3
    public void testRoundTest3() {
        Capitaly game = new Capitaly();
        try {
            game.read("data3.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        ArrayList<Integer> dataList = game.roundTest();
        assertEquals(dataList.get(0), (Integer)1);
        assertEquals(dataList.get(1), (Integer)9000);
        assertEquals(dataList.get(2), (Integer)2);
        assertEquals(dataList.get(3), (Integer)8500);
        assertEquals(dataList.get(4), (Integer)6);
        assertEquals(dataList.get(5), (Integer)10500);
    }
     @Test //4
    public void dieTest() {
        Capitaly game = new Capitaly();
        try {
            game.read("data1.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        for (int i = 0; i < game.board.size(); i++) {
            if (game.board.get(i) instanceof Property) {
                Property prop = (Property) game.board.get(i);
                if (prop.getOwnedBy() == "Gergo") {
                    prop.setOwned(false);
                    prop.setOwnedBy("");
                    game.board.set(i, prop);
                }
            }
        }
        Property p = (Property)game.board.get(2);
        p.setOwned(Boolean.TRUE);
        p.setOwnedBy("Gergo");
        for (int i = 0; i < game.board.size(); i++) {
            if (game.board.get(i) instanceof Property) {
                Property prop = (Property) game.board.get(i);
                if (prop.getOwnedBy() == "Gergo") {
                    prop.setOwned(false);
                    prop.setOwnedBy("");
                    game.board.set(i, prop);
                }
            }
        }
        Property p3 = (Property)game.board.get(2);
        assertFalse(p3.getOwned());
    }
    @Test //5
    public void dieTest2() {
        Capitaly game = new Capitaly();
        try {
            game.read("data6.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        game.roundTest();
        game.die(game.players.get(0));
        Property p3 = (Property)game.board.get(0);
        assertFalse(p3.getOwned());
    }
    @Test //6
    public void endOfGameTest1() {
        Capitaly game = new Capitaly();
        try {
            game.read("data6.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        assertFalse(game.endOfGame());
    }
    @Test //7
    public void endOfGameTest2() {
        Capitaly game = new Capitaly();
        try {
            game.read("data6.txt");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
            System.exit(-1);
        } catch (InvalidInputException ex) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }
        game.deathcounter=2;
        assertTrue(game.endOfGame());
    }
}
