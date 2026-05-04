package game;

public class Monster extends Combatant {
    private int damage;

    public Monster(String name, int hp, int damage) {
        super(name, hp);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
