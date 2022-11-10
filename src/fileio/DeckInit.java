package fileio;

import java.util.ArrayList;

public class DeckInit {

    private ArrayList<Card> deck = new ArrayList<>();

    public DeckInit(ArrayList<CardInput> dec) {
        for(int i = 0; i < dec.size(); ++i) {
            if(dec.get(i).getName().equals("Winterfell") ||
                dec.get(i).getName().equals("Firestorm") ||
                dec.get(i).getName().equals("Heart Hound")) {
                Environment card = new Environment(dec.get(i));
                deck.add(card);
            } else {
                Minion card = new Minion(dec.get(i));
                deck.add(card);
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
