package game;

import java.util.ArrayList;

public class Hero extends Combatant {
    private Weapon weapon;
    private ArrayList<Item> inventory;

    public Hero(String name) {
        super(name);
        this.inventory = new ArrayList<>();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void printInventory() {
        System.out.println("--- Inventario de " + getName() + " ---");
        if (inventory.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Item item : inventory) {
                System.out.println("- " + item);
            }
        }
    }

    public int getTotalGold() {
        int total = 0;
        for (Item item : inventory) {
            total += item.getPrice();
        }
        return total;
    }

    public int getTotalWeight() {
        int total = 0;
        for (Item item : inventory) {
            total += item.getWeight();
        }
        return total;
    }

    public void transferLoot(Monster monster) {
        ArrayList<Item> loot = monster.getLoot();
        if (loot != null && !loot.isEmpty()) {
            inventory.addAll(loot);
            System.out.println(getName() + " recogió " + loot.size() + " items del monstruo.");
        }
    }

    @Override
    public void attack(Character character) {
        if (weapon != null) {
            int damage = weapon.getDamage();
            character.setHp(character.getHp() - damage);
            System.out.println(getName() + " ataca a " + character.getName() + " con " + weapon.getType() + " y realiza " + damage + " daño.");
        } else {
            character.setHp(character.getHp() - 1);
            System.out.println(getName() + " ataca sin arma y hace 1 de daño.");
        }
    }
}
