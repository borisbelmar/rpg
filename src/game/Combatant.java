package game;

public abstract class Combatant extends Character implements Combat {
    public Combatant(String name) {
        super(name);
    }

    public Combatant(String name, int hp) {
        super(name, hp);
    }
}
