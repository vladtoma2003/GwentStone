package fileio;

public class Environment extends Card {
    public Environment(CardInput input) {
        super(input);
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
