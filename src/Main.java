import game.*;

import java.util.ArrayList;
import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingresa el nombre de tu Heroe: ");
    String name = scanner.nextLine();

    Hero hero = new Hero(name);
    Weapon weapon = new Weapon("Espada de Acero", 100, 5);
    hero.setWeapon(weapon);

    System.out.println("¡Bienvenido, " + hero.getName() + "! Tu aventura comienza ahora.");

    ArrayList<Item> goblinLoot = new ArrayList<>();
    goblinLoot.add(new Item("Monedas de oro", 25, 1));
    goblinLoot.add(new Item("Poción de vida", 15, 2));

    Monster monster1 = new Monster("Goblin", 20, 1, goblinLoot);
    monster1.printSpawn();

    Encounter encounter = new Encounter(hero, monster1);
    encounter.startCombat();

    System.out.println();
    hero.printInventory();
    System.out.println("Oro total: " + hero.getTotalGold());
    System.out.println("Peso total: " + hero.getTotalWeight());

    System.out.println();
    System.out.println("=== ¡Una nueva amenaza aparece! ===");
    hero.setHp(50);
    System.out.println(hero.getName() + " se cura a " + hero.getHp() + " HP.");

    ArrayList<Item> dragonLoot = new ArrayList<>();
    dragonLoot.add(new Item("Escamas de dragón", 150, 5));
    dragonLoot.add(new Item("Gema rubí", 200, 1));
    dragonLoot.add(new Item("Poción de vida", 15, 2));

    Monster monster2 = new Monster("Dragón", 50, 4, dragonLoot);
    monster2.printSpawn();

    Encounter encounter2 = new Encounter(hero, monster2);
    encounter2.startCombat();

    System.out.println();
    System.out.println("--- Estado final ---");
    hero.printInventory();
    System.out.println("Oro total: " + hero.getTotalGold());
    System.out.println("Peso total: " + hero.getTotalWeight());
}
