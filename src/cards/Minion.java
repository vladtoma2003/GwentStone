package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.CardInput;


@JsonIgnoreProperties({"frozen", "tank"})

public class Minion extends Card {
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

    public Minion(final CardInput input) {
        super(input);
        attackDamage = input.getAttackDamage();
        health = input.getHealth();
    }

    public Minion(final Minion m) {
        this.setName(m.getName());
        this.setMana(m.getMana());
        this.setDescription(m.getDescription());
        this.setColors(m.getColors());
        this.setHealth(m.getHealth());
        this.setAttackDamage(m.getAttackDamage());
    }

    public Minion() {

    }

    /**
     * Attacks a Minion type card
     *
     * @param attacked
     */
    public void attack(final Minion attacked) {
        attacked.setHealth(attacked.getHealth() - this.getAttackDamage());
        this.setHasAttacked(true);
    }

    /**
     * Attacks a Hero type card
     *
     * @param attacked
     */
    public void attack(final Hero attacked) {
        attacked.setHealth(attacked.getHealth() - this.getAttackDamage());
        this.setHasAttacked(true);
    }

    /**
     * Does nothing, is overwritten in classes that have abilities
     *
     * @param attacked
     */
    public void useAbility(final Minion attacked) {
    }

    /**
     * Returns the attack damage of the card
     *
     * @return
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * Sets the attack damage of the card
     *
     * @param attackDamage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Returns the current health of the card
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the card
     *
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns the row that the card is placed on
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row that the card is placed on
     *
     * @param row
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * Returns if the card is frozen
     *
     * @return
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Sets if the card is frozen
     *
     * @param frozen
     */
    public void setFrozen(final boolean frozen) {
        isFrozen = frozen;
    }

    /**
     * Returns if the card has already attacked this round
     *
     * @return
     */
    public boolean isHasAttacked() {
        return hasAttacked;
    }

    /**
     * Sets if the card has already attacked this round
     *
     * @param hasAttacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * Returns if the card is a tank
     *
     * @return
     */
    public boolean isTank() {
        return isTank;
    }

    /**
     * Sets the tank status of the card
     *
     * @param tank
     */
    public void setTank(final boolean tank) {
        isTank = tank;
    }

    /**
     * Print format for minion type card
     *
     * @return
     */
    @Override
    public String toString() {
        return "CardInput{"
                + "mana="
                + getMana()
                + ", attackDamage="
                + attackDamage
                + ", health="
                + health
                + ", description='"
                + getDescription()
                + '\''
                + ", colors="
                + getColors()
                + ", name='"
                + ""
                + getName()
                + '\''
                + '}';
    }
}
