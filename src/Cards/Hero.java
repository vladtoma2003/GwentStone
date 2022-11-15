package Cards;

import Cards.Card;
import fileio.CardInput;

public class Hero extends Card {
    private int health;

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

    @Override
    public String toString() {
        return "CardInput{"
                +  "mana="
                + getMana()
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
