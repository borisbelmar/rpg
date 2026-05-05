package game;

import java.util.ArrayList;

public class Monster extends Combatant {
    private int damage;
    private ArrayList<Item> loot;

    public Monster(String name, int hp, int damage, ArrayList<Item> loot) {
        super(name, hp);
        this.damage = damage;
        this.loot = loot;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }

    public void printSpawn() {
        System.out.println("¡Cuidado! Un " + this.getName() + " ha aparecido con " + this.getHp() + " HP.");
    }

    @Override
    public void attack(Character character) {
        character.setHp(character.getHp() - damage);
        System.out.println(getName() + " ataca a " + character.getName() + " y realiza " + damage + " daño.");
    }
}
