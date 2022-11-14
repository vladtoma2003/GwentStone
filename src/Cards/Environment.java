package Cards;

import fileio.CardInput;

public class Environment extends Card {
    public Environment(CardInput input) {
        super(input);
    }

    public Environment(Card input) {
        super(input);
    }

    public Environment() {

    }

    @Override
    public String toString() {
        return "CardInput{"
                +  "mana="
                + getMana()
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
