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
public class Berserker extends Ork {

    public Berserker(String name, int HP, int attack) {
        super(name, HP, attack);
    }

    public void applyDemageFromCharacter(Character a) {
        HP -= a.attack * 2;
    }
}
