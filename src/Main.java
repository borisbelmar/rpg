import game.*;
import game.Character;

import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingresa el nombre de tu Heroe: ");
    String name = scanner.nextLine();

    Hero hero = new Hero(name);
    Weapon weapon = new Weapon("Espada de Acero", 100, 5);
    hero.setWeapon(weapon);

    System.out.println("¡Bienvenido, " + hero.getName() + "! Tu aventura comienza ahora.");

    System.out.println(hero);

    Monster monster1 = new Monster("Goblin", 20, 1);

    monster1.printSpawn();

    Encounter encounter = new Encounter(hero, monster1);

    encounter.startCombat();
}
