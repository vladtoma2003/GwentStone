package Cards;

import Cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;

public class Hero extends Card {
    private int health;
    @JsonIgnore
    private boolean hasAttacked = false;

    public Hero(CardInput card) {
        super(card);
        this.health = 30;
    }

    public Hero(Hero hero) {
        this.setHealth(hero.getHealth());
        this.setColors(hero.getColors());
        this.setDescription(hero.getDescription());
        this.setMana(hero.getMana());
        this.setName(hero.getName());
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

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
