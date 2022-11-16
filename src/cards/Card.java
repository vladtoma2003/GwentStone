package cards;

import fileio.CardInput;

import java.util.ArrayList;

public class Card {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public Card(final CardInput card) {
        mana = card.getMana();
        description = card.getDescription();
        colors = new ArrayList<>(card.getColors());
        name = card.getName();
    }

    public Card(final Card card) {
        mana = card.getMana();
        description = card.getDescription();
        colors = new ArrayList<>(card.getColors());
        name = card.getName();
    }

    public Card() {
    }

    /**
     * returns mana of the card
     *
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * changes the value of the mana
     *
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * returns the description of a card
     *
     * @return
     */

    public String getDescription() {
        return description;
    }

    /**
     * changes the description of a card
     *
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * returns the array of colors of a card
     *
     * @return
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * sets the colors of a card
     *
     * @param colors
     */

    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * returns the name of the card
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of the card
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Print format of the card
     *
     * @return
     */
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
