package Cards;

import fileio.CardInput;

public class Environment extends Card {
    public Environment(final CardInput input) {
        super(input);
    }

    public Environment(final Card input) {
        super(input);
    }

    public Environment() {

    }

    @Override
    public String toString() {
        return "CardInput{"
                + "mana="
                + getMana()
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
