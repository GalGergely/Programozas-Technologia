package rpg;

public abstract class Character {

    public String name;
    public int HP;
    public int attack;

    public Character(String name, int HP, int attack) {
        this.name = name;
        this.HP = HP;
        this.attack = attack;
    }

    public void attack(Character a) {
        if (isAlive()) {
            attacked(this);
        }
    }

    public void attacked(Character a) {
        if (isAlive()) {
            applyDemageFromCharacter(a);
        }
    }

    public void applyDemageFromCharacter(Character a) {
        HP -= a.attack;
    }

    public Boolean isAlive() {
        return this.HP > 0;
    }
}
