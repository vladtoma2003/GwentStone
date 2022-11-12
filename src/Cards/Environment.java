package Cards;

import fileio.CardInput;

public class Environment extends Card {
    public Environment(CardInput input) {
        super(input);
    }

//    public Environment(Environment e) {
//        Environment ev = new Environment();
//        ev.setName(e.getName());
//        ev.setMana(e.getMana());
//        ev.setDescription(e.getDescription());
//        ev.setColors(e.getColors());
//    }

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
