package CustomClasses;

import Cards.*;
import fileio.CardInput;

import java.util.ArrayList;

public class DeckInit {

    private ArrayList<Card> deck = new ArrayList<>();

    public DeckInit(ArrayList<CardInput> dec) {
        for (CardInput c : dec) {
            if (c.getName().equals("Winterfell") ||
                    c.getName().equals("Firestorm") ||
                    c.getName().equals("Heart Hound")) {
                Environment card = new Environment(c);
                deck.add(card);
            } else {
                switch (c.getName()) {
                    case "The Ripper" -> {
                        TheRipper card = new TheRipper(c);
                        deck.add(card);
                    }
                    case "Miraj" -> {
                        Miraj card = new Miraj(c);
                        deck.add(card);
                    }
                    case "Goliath" -> {
                        Goliath card = new Goliath(c);
                        deck.add(card);
                    }
                    case "Warden" -> {
                        Warden card = new Warden(c);
                        deck.add(card);
                    }
                    case "Sentinel" -> {
                        Sentinel card = new Sentinel(c);
                        deck.add(card);
                    }
                    case "Berserker" -> {
                        Berserker card = new Berserker(c);
                        deck.add(card);
                    }
                    case "The Cursed One" -> {
                        CursedOne card = new CursedOne(c);
                        deck.add(card);
                    }
                    case "Disciple" -> {
                        Disciple card = new Disciple(c);
                        deck.add(card);
                    }
                }
//                Minion card = new Minion(c);
//                deck.add(card);
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
