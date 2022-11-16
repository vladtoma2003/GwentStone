package classes.myClasses;

import cards.Card;
import cards.Hero;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> deck;
    private ArrayList<Card> hand;
    private Hero hero;
    private int playerIdx;

    private int mana = 1;

    private int closestRow;


    public Player(final ArrayList<Card> deck, final Hero hero, final int playerIdx) {
        this.deck = deck;
        this.hero = hero;
        this.playerIdx = playerIdx;
        hand = new ArrayList<>();
    }

    /**
     * Return the deck of the player
     *
     * @return
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Sets the deck of the player
     *
     * @param deck
     */
    public void setDeck(final ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Returns the hand of the player
     *
     * @return
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Sets the hand of the player
     *
     * @param hand
     */
    public void setHand(final ArrayList<Card> hand) {
        this.hand = hand;
    }

    /**
     * Returns the Hero type card
     *
     * @return
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * Sets the Hero type card
     *
     * @param hero
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     * Returns the player's index
     *
     * @return
     */
    public int getPlayerIdx() {
        return playerIdx;
    }

    /**
     * Sets the player's index
     *
     * @param playerIdx
     */

    public void setPlayerIdx(final int playerIdx) {
        this.playerIdx = playerIdx;
    }

    /**
     * Returns how much mana the player has
     *
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * Sets the amount of mana that the player has
     *
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * Returns the closest row of the player
     *
     * @return
     */
    public int getClosestRow() {
        return closestRow;
    }

    /**
     * Sets the closest row to the player
     *
     * @param closestRow
     */

    public void setClosestRow(final int closestRow) {
        this.closestRow = closestRow;
    }

}
