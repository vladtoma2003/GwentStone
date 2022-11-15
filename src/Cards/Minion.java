package Cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;


@JsonIgnoreProperties({ "frozen", "tank"})

public class Minion extends Card{
    private int attackDamage;
    private int health;
    @JsonIgnore
    private int row;

    @JsonIgnore
    private boolean hasAttacked = false;

    @JsonIgnore
    private boolean isFrozen = false;

    @JsonIgnore
    private boolean isTank;

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

    public void Attack(Minion attacked) {
        attacked.setHealth(attacked.getHealth() - this.getAttackDamage());
        this.setHasAttacked(true);
    }

    public void Attack(Hero attacked) {
        attacked.setHealth(attacked.getHealth() - this.getAttackDamage());
        this.setHasAttacked(true);
    }

    public void useAbility(Minion attacked){};

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

    public boolean isHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public boolean isTank() {
        return isTank;
    }

    public void setTank(boolean tank) {
        isTank = tank;
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
