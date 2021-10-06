/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

import java.io.FileNotFoundException;

/**
 *
 * @author Greg
 */
public class Main {
    public static void main(String[] args) {
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
        game.simulation();
    }
}
