package game;

public class Hero extends Combatant {
    private Weapon weapon;

    public Hero(String name) {
        super(name);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public void attack(Character character) {
        if (weapon != null) {
            int damage = weapon.getDamage();
            character.setHp(character.getHp() - damage);
            System.out.println(getName() + " ataca a " + character.getName() + " con " + weapon.getType() + " y realiza " + damage + " daño.");
        } else {
            character.setHp(character.getHp() - 1); // Daño mínimo sin arma
            System.out.println(getName() + " ataca sin arma y hace 1 de daño.");
        }
    }
}
