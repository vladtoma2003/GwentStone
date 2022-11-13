package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Minion extends Card{
    private int attackDamage;
    private int health;
    @JsonIgnore
    private int row;

    @JsonIgnore
    private boolean isFrozen = false;

    public Minion(CardInput input) {
        super(input);
        attackDamage = input.getAttackDamage();
        health = input.getHealth();
    }

    public Minion(Minion m) {
        this.setName(m.getName());
        this.setMana(m.getMana());
        this.setDescription(m.getDescription());
        this.setColors(m.getColors());
        this.setHealth(m.getHealth());
        this.setAttackDamage(m.getAttackDamage());
    }

    public Minion() {

    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    @Override
    public String toString() {
        return "CardInput{"
                +  "mana="
                + getMana()
                +  ", attackDamage="
                + attackDamage
                + ", health="
                + health
                +  ", description='"
                + getDescription()
                + '\''
                + ", colors="
                + getColors()
                + ", name='"
                +  ""
                + getName()
                + '\''
                + '}';
    }
}
