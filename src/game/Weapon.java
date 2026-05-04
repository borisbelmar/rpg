package game;

public class Weapon extends Item {
    private int damage;
    private String type;

    public Weapon(String name, int price, int damage) {
        super(name, price);
        this.damage = damage;
        this.type = "NEUTRAL";
    }

    public Weapon(String name, int price, int damage, String type) {
        super(name, price);
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
