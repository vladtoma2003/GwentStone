package CustomClasses;

import Cards.*;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private Hero hero;
    private int playerIdx;

    private int mana = 1;

    private int closestRow;

    public Player(ArrayList<Card> deck, Hero hero, int playerIdx) {
        this.deck = deck;
        this.hero = hero;
        this.playerIdx = playerIdx;
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public void setPlayerIdx(int playerIdx) {
        this.playerIdx = playerIdx;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getClosestRow() {
        return closestRow;
    }

    public void setClosestRow(int closestRow) {
        this.closestRow = closestRow;
    }
}
