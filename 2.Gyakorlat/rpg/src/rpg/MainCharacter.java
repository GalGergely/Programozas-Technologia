/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author Greg
 */
public class MainCharacter extends Character{
    public double deffense;

    public MainCharacter(int deffense, String name, int HP, int attack) {
        super(name, HP, attack);
        this.deffense = deffense;
    }

    @Override
    public void applyDemageFromCharacter(Character a) {
        HP-=(int)(a.attack/deffense);
    }
}
