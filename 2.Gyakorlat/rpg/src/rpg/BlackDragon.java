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
public class BlackDragon extends Dragon {

    public BlackDragon(String name, int HP, int attack) {
        super(name, HP, attack);
        this.ATTACK_THRESHOLD = 20;
    }

    @Override
    public void applyDemageFromCharacter(Character a) {
        if (a.attack > ATTACK_THRESHOLD) {
            HP -= a.attack;
        }
    }
}
