# 🗡️ Proyecto RPG — Los 4 Pilares de la POO en Java

Este proyecto es un juego de rol (RPG) por consola desarrollado en Java. Su propósito es ilustrar de forma práctica los **4 pilares de la Programación Orientada a Objetos (POO)**:

1. [Encapsulamiento](#1️⃣-encapsulamiento)
2. [Herencia](#2️⃣-herencia)
3. [Polimorfismo](#3️⃣-polimorfismo)
4. [Abstracción](#4️⃣-abstracción)

---

## 🗂️ Estructura del proyecto

```
rpg/
└── src/
    ├── Main.java
    └── game/
        ├── Character.java   ← Clase base abstracta
        ├── Combatant.java   ← Clase intermedia abstracta
        ├── Combat.java      ← Interfaz de combate
        ├── Hero.java        ← Personaje jugable
        ├── Monster.java     ← Enemigo
        ├── Item.java        ← Objeto base
        ├── Weapon.java      ← Arma (extiende Item)
        └── Encounter.java   ← Gestiona el combate
```

---

## 🏗️ Desarrollo paso a paso desde cero

### Paso 1 — `Item.java`: La base de los objetos del mundo

Lo primero que necesitamos es representar cualquier **objeto** del mundo del juego. Todo ítem tiene un nombre y un precio.

```java
package game;

public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
```

> 🔒 **ENCAPSULAMIENTO:** `name` y `price` son `private`. Nadie puede acceder ni modificar estos valores directamente desde fuera de la clase. Solo se pueden establecer al momento de crear el objeto mediante el constructor.

---

### Paso 2 — `Weapon.java`: Especializando un ítem

Una espada no es solo un ítem genérico, también tiene daño y tipo. Creamos `Weapon` extendiendo `Item`.

```java
package game;

public class Weapon extends Item {
    private int damage;
    private String type;

    public Weapon(String name, int price, int damage) {
        super(name, price);       // Reutiliza el constructor de Item
        this.damage = damage;
        this.type = "NEUTRAL";    // Valor por defecto
    }

    public Weapon(String name, int price, int damage, String type) {
        super(name, price);
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
```

> 🧬 **HERENCIA:** `Weapon extends Item` significa que `Weapon` hereda los atributos `name` y `price` de `Item` sin tener que redefinirlos. Usa `super(name, price)` para llamar al constructor del padre.

> 🔒 **ENCAPSULAMIENTO:** `damage` y `type` también son `private`, accesibles únicamente a través de sus *getters* y *setters*.

---

### Paso 3 — `Character.java`: La base de todos los personajes

Todo personaje del juego (héroe o monstruo) tiene un **nombre** y puntos de vida (**HP**). Creamos una clase abstracta que servirá de base para todos.

```java
package game;

public abstract class Character {
    private String name;
    private int hp;

    public Character(String name) {
        this.name = name;
        this.hp = 10;           // HP por defecto
    }

    public Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    @Override
    public String toString() {
        return "Character{name='" + name + "', hp=" + hp + '}';
    }
}
```

> 🔒 **ENCAPSULAMIENTO:** `name` y `hp` son `private`. Para leerlos o modificarlos se usan exclusivamente `getName()`, `getHp()`, `setName()` y `setHp()`. Esto protege la integridad de los datos.

> 🧩 **ABSTRACCIÓN:** La clase es `abstract`, lo que significa que **no se puede instanciar directamente**. No tiene sentido crear un `new Character()` genérico; solo existen héroes y monstruos concretos. La abstracción nos obliga a trabajar con subclases que representen algo real.

---

### Paso 4 — `Combat.java`: Definir el contrato de combate

Antes de crear combatientes, definimos **qué capacidad** debe tener cualquier entidad que pueda luchar: atacar. Esto lo hacemos con una **interfaz**.

```java
package game;

public interface Combat {
    void attack(Character character);
}
```

> 🧩 **ABSTRACCIÓN:** Una interfaz es el nivel más alto de abstracción. Define el **"qué"** (atacar a un personaje), pero no el **"cómo"**. Cada clase que la implemente decidirá su propio comportamiento de ataque.

---

### Paso 5 — `Combatant.java`: El puente entre personaje y combatiente

`Combatant` es la clase que une a `Character` (ser vivo con nombre y HP) con `Combat` (capacidad de atacar). Es abstracta porque aún no sabemos si será héroe o monstruo.

```java
package game;

public abstract class Combatant extends Character implements Combat {

    public Combatant(String name) {
        super(name);
    }

    public Combatant(String name, int hp) {
        super(name, hp);
    }
}
```

> 🧬 **HERENCIA:** `Combatant` hereda de `Character` (tiene nombre y HP) **y además** implementa la interfaz `Combat` (puede atacar). Así combina herencia de clase e implementación de interfaz.

> 🧩 **ABSTRACCIÓN:** Sigue siendo `abstract` porque no implementa el método `attack()`. Eso queda pendiente para las clases concretas `Hero` y `Monster`.

---

### Paso 6 — `Hero.java`: El personaje jugable

El héroe es un combatiente que puede equiparse con un arma. Su forma de atacar depende de si tiene arma o no.

```java
package game;

public class Hero extends Combatant {
    private Weapon weapon;

    public Hero(String name) {
        super(name);
    }

    public Weapon getWeapon() { return weapon; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    @Override
    public void attack(Character character) {
        if (weapon != null) {
            int damage = weapon.getDamage();
            character.setHp(character.getHp() - damage);
            System.out.println(getName() + " ataca a " + character.getName() +
                " con " + weapon.getType() + " y realiza " + damage + " daño.");
        } else {
            character.setHp(character.getHp() - 1); // Daño mínimo sin arma
            System.out.println(getName() + " ataca sin arma y hace 1 de daño.");
        }
    }
}
```

> 🧬 **HERENCIA:** `Hero extends Combatant`, por lo tanto hereda `name`, `hp` y todos sus métodos de `Character`, más la obligación de implementar `attack()` de `Combat`.

> 🔄 **POLIMORFISMO:** `attack()` está anotado con `@Override`. El héroe tiene su **propia lógica de ataque**: consulta su arma, calcula el daño y lo aplica. Esta implementación es completamente diferente a la del monstruo.

> 🔒 **ENCAPSULAMIENTO:** `weapon` es `private`. Nadie equipa al héroe directamente; solo a través de `setWeapon()`.

---

### Paso 7 — `Monster.java`: El enemigo

El monstruo es otro combatiente. Tiene un daño fijo y su forma de atacar es directa.

```java
package game;

public class Monster extends Combatant {
    private int damage;

    public Monster(String name, int hp, int damage) {
        super(name, hp);
        this.damage = damage;
    }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public void printSpawn() {
        System.out.println("¡Cuidado! Un " + this.getName() +
            " ha aparecido con " + this.getHp() + " HP.");
    }

    @Override
    public void attack(Character character) {
        character.setHp(character.getHp() - damage);
        System.out.println(getName() + " ataca a " + character.getName() +
            " y realiza " + damage + " daño.");
    }
}
```

> 🔄 **POLIMORFISMO:** `attack()` también es `@Override`, pero con una implementación **completamente diferente** a la del héroe: siempre aplica su daño fijo sin comprobar armas. Mismo método, comportamiento distinto según la clase.

> 🧬 **HERENCIA:** `Monster extends Combatant` → hereda todo de `Character` + debe implementar `attack()`.

---

### Paso 8 — `Encounter.java`: El escenario del combate

Esta clase gestiona el enfrentamiento entre dos combatientes. No le importa si son héroes o monstruos; solo sabe que son `Combatant`.

```java
package game;

public class Encounter {
    private Combatant combatant1;
    private Combatant combatant2;

    public Encounter(Combatant combatant1, Combatant combatant2) {
        this.combatant1 = combatant1;
        this.combatant2 = combatant2;
    }

    public Combatant getCombatant1() { return combatant1; }
    public void setCombatant1(Combatant combatant1) { this.combatant1 = combatant1; }

    public Combatant getCombatant2() { return combatant2; }
    public void setCombatant2(Combatant combatant2) { this.combatant2 = combatant2; }

    public void startCombat() {
        while (combatant1.getHp() > 0 && combatant2.getHp() > 0) {
            combatant1.attack(combatant2);
            if (combatant2.getHp() <= 0) {
                System.out.println(combatant1.getName() + " gana la batalla!");
                break;
            }
            combatant2.attack(combatant1);
            if (combatant1.getHp() <= 0) {
                System.out.println(combatant2.getName() + " gana la batalla!");
                break;
            }
        }
    }
}
```

> 🔄 **POLIMORFISMO en su máxima expresión:** `combatant1.attack(combatant2)` funciona **sin saber** si `combatant1` es un `Hero` o un `Monster`. Java resuelve en tiempo de ejecución qué versión de `attack()` ejecutar. Esto se llama **despacho dinámico**.

> 🔒 **ENCAPSULAMIENTO:** Los combatientes son `private`. Solo se acceden o cambian mediante sus *getters* y *setters*.

---

### Paso 9 — `Main.java`: El punto de entrada

Aquí se ensambla todo el proyecto. Es donde el usuario interactúa y los objetos cobran vida.

```java
import game.*;

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
```

**¿Qué ocurre aquí?**

1. Se pide el nombre del héroe por consola.
2. Se crea un `Hero` con ese nombre.
3. Se crea una `Weapon` (Espada de Acero, daño 5) y se equipa al héroe.
4. Se crea un `Monster` (Goblin, 20 HP, daño 1).
5. Se crea un `Encounter` con ambos y se inicia el combate.
6. El bucle en `startCombat()` alterna ataques hasta que uno caiga.

---

## 🧱 Resumen visual de los 4 pilares

```
┌─────────────────────────────────────────────────────────────────┐
│                     <<interface>>                               │
│                        Combat                                   │
│                    + attack(Character)                          │
└──────────────────────────┬──────────────────────────────────────┘
                           │ implements
┌──────────────────────────▼──────────────────────────────────────┐
│                  (abstract) Character                           │
│  - name: String  [PRIVATE]   ← 🔒 ENCAPSULAMIENTO              │
│  - hp: int       [PRIVATE]   ← 🔒 ENCAPSULAMIENTO              │
│  + getName() / setName()                                        │
│  + getHp()  / setHp()                                           │
└──────────────────────────┬──────────────────────────────────────┘
                           │ extends
┌──────────────────────────▼──────────────────────────────────────┐
│                  (abstract) Combatant                           │
│           🧬 HERENCIA de Character + Combat                     │
│           🧩 ABSTRACCIÓN: no implementa attack()               │
└──────────┬───────────────────────────────┬──────────────────────┘
           │ extends                       │ extends
┌──────────▼──────────┐        ┌───────────▼──────────────────────┐
│       Hero          │        │           Monster                │
│ - weapon: Weapon    │        │  - damage: int                   │
│ + attack() distinto │        │  + attack() distinto             │
│   🔄 POLIMORFISMO   │        │    🔄 POLIMORFISMO               │
└─────────────────────┘        └──────────────────────────────────┘

┌──────────────────────────────────────────────────────────────────┐
│                         Item                                    │
│  - name: String  [PRIVATE]   ← 🔒 ENCAPSULAMIENTO              │
│  - price: int    [PRIVATE]   ← 🔒 ENCAPSULAMIENTO              │
└──────────────────────────┬───────────────────────────────────────┘
                           │ extends
┌──────────────────────────▼───────────────────────────────────────┐
│                        Weapon                                   │
│  - damage: int                                                  │
│  - type: String                                                 │
│  🧬 HERENCIA de Item                                            │
└──────────────────────────────────────────────────────────────────┘
```

---

## 📌 Tabla resumen de los 4 pilares

| Pilar | ¿Dónde aparece en el proyecto? |
|---|---|
| 🔒 **Encapsulamiento** | Todos los atributos son `private` en `Character`, `Item`, `Weapon`, `Monster`, `Hero`. Se acceden con *getters/setters*. |
| 🧬 **Herencia** | `Weapon → Item`, `Combatant → Character`, `Hero → Combatant`, `Monster → Combatant`. |
| 🔄 **Polimorfismo** | `Hero` y `Monster` implementan `attack()` de forma diferente. `Encounter` los usa sin distinguir cuál es cuál. |
| 🧩 **Abstracción** | `Character` y `Combatant` son clases `abstract`. `Combat` es una `interface`. Ninguna puede instanciarse directamente. |

