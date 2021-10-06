/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capitaly;

/**
 *
 * @author Greg
 */
abstract class Player {
    String name;
    char strategy;
    int money;
    int location;

    public Player(String name, char strategy) {
        this.name = name;
        this.strategy = strategy;
        this.money=10000; //mindenki ennyivel indul
        this.location=-1; //nincs rajt a palyan
    }

    public String getName() {
        return name;
    }

    public char getStrategy() {
        return strategy;
    }
    
    @Override
    public String toString() {
        return "Boardpiece{" + "Strategy=" + strategy + ", Name=" + name + '}';
    }
    
}
