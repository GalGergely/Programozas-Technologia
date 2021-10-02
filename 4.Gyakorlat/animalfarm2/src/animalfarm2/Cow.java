/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animalfarm2;

/**
 *
 * @author Greg
 */
public class Cow extends Animal {
    
    public Cow(String name, int weight) {
        super(name, weight, "C");
        MALNOURISHED_THRESHOLD = 100;
    }
}
