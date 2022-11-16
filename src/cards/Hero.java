package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Hero extends Card {
    private final int heroHealth = 30;
    private int health;
    @JsonIgnore
    private boolean hasAttacked = false;

    public Hero(final CardInput card) {
        super(card);
        this.health = heroHealth;
    }

    public Hero(final Hero hero) {
        this.setHealth(hero.getHealth());
        this.setColors(hero.getColors());
        this.setDescription(hero.getDescription());
        this.setMana(hero.getMana());
        this.setName(hero.getName());
    }

    /**
     * Returns the health of the hero
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the hero
     *
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns if the hero has already attacked this round
     *
     * @return
     */
    public boolean isHasAttacked() {
        return hasAttacked;
    }

    /**
     * Sets the hero's attack status for the round
     *
     * @param hasAttacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * Print format for hero type card
     *
     * @return
     */
    @Override
    public String toString() {
        return "CardInput{"
                + "mana="
                + getMana()
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
