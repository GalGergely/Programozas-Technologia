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
public class Tactical extends Player {

    private boolean bought=false;
    
    public Tactical(String name) {
        super(name,'T');
    }
    /**
     * 
     * @return if he bought in the privious rond or not
     */
    public boolean getBought() {
        return bought;
    }
     /**
     * 
     * you can set if he bought in the privious rond or not
     */
    public void setBought(boolean bought) {
        this.bought = bought;
    }
    
}
