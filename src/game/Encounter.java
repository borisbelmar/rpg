package game;

public class Encounter {
    private Combatant combatant1;
    private Combatant combatant2;

    public Encounter(Combatant combatant1, Combatant combatant2) {
        this.combatant1 = combatant1;
        this.combatant2 = combatant2;
    }

    public Combatant getCombatant1() {
        return combatant1;
    }

    public void setCombatant1(Combatant combatant1) {
        this.combatant1 = combatant1;
    }

    public Combatant getCombatant2() {
        return combatant2;
    }

    public void setCombatant2(Combatant combatant2) {
        this.combatant2 = combatant2;
    }

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
