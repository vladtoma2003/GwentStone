package fileio;

import java.util.ArrayList;

public abstract class Card {
    private int mana;
    private String description;
    private ArrayList<String> colors;
    private String name;

    public Card (CardInput card) {
        mana = card.getMana();
        description = card.getDescription();
        colors = new ArrayList<>(card.getColors());
        name = card.getName();
    }
    public Card() {}

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
