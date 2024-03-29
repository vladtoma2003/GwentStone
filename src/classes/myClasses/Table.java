package classes.myClasses;

import cards.Minion;
import cards.Card;
import cards.Hero;

import java.util.ArrayList;

public class Table {

    private ArrayList<Minion>[] table;
    private int currTurn;
    private final int bound = 5;
    private final int four = 4;
    private final int three = 3;
    private final int two = 2;
    private final int one = 1;
    private final int zero = 0;

    public Table(ArrayList<Minion>[] tabel) {
        tabel = this.table.clone();
    }

    public Table(final int turn) {
        table = new ArrayList[four];
        for (int i = 0; i < four; ++i) {
            table[i] = new ArrayList<Minion>();
        }
        currTurn = turn;
    }

    /**
     * Checks if the given row is full
     *
     * @param row
     * @return
     */
    private boolean isFullRow(final int row) {
        return bound <= table[row].size();
    }

    /**
     * Places a given Minion type card on the table by removing it from the hand
     * and adding it to the corresponding row
     *
     * @param p
     * @param idx
     * @param currPlayer
     * @param err
     */
    public void placeCard(final Player p, final int idx, final int currPlayer, final Error err) {
        if (idx >= p.getHand().size()) {
            return;
        }
        if (p.getHand().get(idx).getName().equals("Winterfell")
                || p.getHand().get(idx).getName().equals("Firestorm")
                || p.getHand().get(idx).getName().equals("Heart Hound")) {
            err.setErr(true);
            err.setMessage("Cannot place environment card on table.");
            return;
        }
        Minion card = (Minion) p.getHand().get(idx);
        int row = zero;
        if (currPlayer == 0) {
            if (card.getRow() == 1) {
                row = two;
            } else {
                row = three;
            }
        } else {
            if (card.getRow() == 1) {
                row = one;
            }
        }
        int mana = card.getMana();
        if (mana > p.getMana()) {
            err.setErr(true);
            err.setMessage("Not enough mana to place card on table.");
            return;
        }
        if (isFullRow(row)) {
            err.setErr(true);
            err.setMessage("Cannot place card on table since row is full.");
            return;
        }
        p.setMana(p.getMana() - mana);
        p.getHand().remove(idx);
        table[row].add(card);
    }

    /**
     * Returns a copy of the given row from the table
     *
     * @param row
     * @return
     */
    public ArrayList<Card> getRow(final int row) {
        ArrayList<Card> ret = new ArrayList<>(table[row]);
        return ret;
    }

    /**
     * Returns the card position from the table (if the card is on the table)
     *
     * @param x
     * @param y
     * @param err
     * @return
     */
    public Minion getCardAtPosition(final int x, final int y, final Error err) {
        if (table == null) {
            return null;
        }
        if (x > three) {
            return null;
        }
        int size = table[x].size();
        if (y >= size) {
            err.setErr(true);
            err.setMessage("No card available at that position.");
            return null;
        }
        if (y >= table[x].size()) {
            err.setErr(true);
            err.setMessage("No card available at that position.");
            return null;
        }
        Minion ret = new Minion(table[x].get(y));
        return ret;
    }

    /**
     * Uses the ability of an Environment type card on the given row
     *
     * @param player
     * @param idx
     * @param row
     * @param err
     */
    public void useEnvCard(final Player player, final int idx, final int row, final Error err) {
        Card card = player.getHand().get(idx);
        if (!(card.getName().equals("Winterfell")
                || card.getName().equals("Firestorm")
                || card.getName().equals("Heart Hound"))) {
            err.setErr(true);
            err.setMessage("Chosen card is not of type environment.");
            return;
        }
        if (card.getMana() > player.getMana()) {
            err.setErr(true);
            err.setMessage("Not enough mana to use environment card.");
            return;
        }
        if (belongsToCurrPlayer(row)) {
            err.setErr(true);
            err.setMessage("Chosen row does not belong to the enemy.");
            return;
        }
        switch (card.getName()) {
            case "Firestorm":
                for (int i = 0; i < table[row].size(); ++i) {
                    Minion currCard = table[row].get(i);
                    currCard.setHealth(currCard.getHealth() - 1);
                }
                checkHealth();
                break;
            case "Winterfell":
                Minion c;
                for (int i = 0; i < table[row].size(); ++i) {
                    c = table[row].get(i);
                    c.setFrozen(true);
                }
                break;
            // HeartHound
            default:
                Minion stolenCard = table[row].get(0);
                for (int i = 1; i < table[row].size(); ++i) {
                    Minion minion = table[row].get(i);
                    if (stolenCard.getHealth() > minion.getHealth()) {
                        stolenCard = minion;
                    }
                }
                int newRow = three - row;
                if (isFullRow(newRow)) {
                    err.setErr(true);
                    err.setMessage("Cannot steal enemy card since the player's row is full.");
                    return;
                }
                table[row].remove(stolenCard);
                table[newRow].add(stolenCard);
                break;

        }
        player.getHand().remove(card);
        player.setMana(player.getMana() - card.getMana());
    }

    /**
     * Returns all the frozen cards on the table
     *
     * @return
     */
    public ArrayList<Minion> getFrozenCards() {
        ArrayList<Minion> arr = new ArrayList<>();
        for (int i = 0; i <= three; ++i) {
            for (var c : table[i]) {
                Minion ceva = (Minion) c;
                if (ceva.isFrozen()) {
                    arr.add(ceva);
                }
            }
        }
        return arr;

    }

    /**
     * The attacker card attacks the attacked card, both have to be present on the table
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param err
     */
    public void attack(final int x1, final int y1, final int x2, final int y2, final Error err) {
        Minion attacker = table[x1].get(y1);
        Minion attacked = table[x2].get(y2);
        if (belongsToCurrPlayer(x2)) {
            err.setErr(true);
            err.setMessage("Attacked card does not belong to the enemy.");
            return;
        }
        if (attacker.isHasAttacked()) {
            err.setErr(true);
            err.setMessage("Attacker card has already attacked this turn.");
            return;
        }
        if (attacker.isFrozen()) {
            err.setErr(true);
            err.setMessage("Attacker card is frozen.");
            return;
        }
        if (!attacked.isTank() && isEnemyTank()) {
            err.setErr(true);
            err.setMessage("Attacked card is not of type 'Tank'.");
            return;
        }
        attacker.attack(attacked);
        checkHealth();
    }

    /**
     * Checks if the enemy has a tank present on the table
     *
     * @return
     */
    public boolean isEnemyTank() {
        if (currTurn == 0) {
            for (var c : table[1]) {
                if (c.isTank()) {
                    return true;
                }
            }
        } else {
            for (var c : table[2]) {
                if (c.isTank()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Uses the ability of a given Minion type card on the
     * card that is at the given position
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param err
     */
    public void useCardAbility(final int x1, final int y1, final int x2,
                               final int y2, final Error err) {
        var attacker = table[x1].get(y1);
        var attacked = table[x2].get(y2);

        if (attacker.isFrozen()) {
            err.setErr(true);
            err.setMessage("Attacker card is frozen.");
            return;
        }
        if (attacker.isHasAttacked()) {
            err.setErr(true);
            err.setMessage("Attacker card has already attacked this turn.");
            return;
        }
        if (attacker.getName().equals("Disciple") && !belongsToCurrPlayer(x2)) {
            err.setErr(true);
            err.setMessage("Attacked card does not belong to the current player.");
            return;
        }
        if (!(attacker.getName().equals("Disciple")) && belongsToCurrPlayer(x2)) {
            err.setErr(true);
            err.setMessage("Attacked card does not belong to the enemy.");
            return;
        }
        if (!attacker.getName().equals("Disciple") && !attacked.isTank() && isEnemyTank()) {
            err.setErr(true);
            err.setMessage("Attacked card is not of type 'Tank'.");
            return;
        }

        attacker.useAbility(attacked);
        checkHealth();

    }

    /**
     * The given card attacks the hero if there is no enemy tank presnet
     *
     * @param game
     * @param hero
     * @param x
     * @param y
     * @param err
     */
    public void attackHero(final Game game, final Hero hero,
                           final int x, final int y, final Error err) {
        var attacker = table[x].get(y);
        if (err.getErr()) {
            return;
        }
        if (attacker.isFrozen()) {
            err.setErr(true);
            err.setMessage("Attacker card is frozen.");
            return;
        }
        if (attacker.isHasAttacked()) {
            err.setErr(true);
            err.setMessage("Attacker card has already attacked this turn.");
            return;
        }
        if (isEnemyTank()) {
            err.setErr(true);
            err.setMessage("Attacked card is not of type 'Tank'.");
            return;
        }
        attacker.attack(hero);
        checkHeroHealth(game);
    }

    /**
     * Checks if the hero has died and if so the game ends
     *
     * @param game
     */
    public void checkHeroHealth(final Game game) {
        if (game.getPlayers().get(1).getHero().getHealth() <= 0) {
            game.setGameEnded(true);
            game.setGameEndMessage("Player one killed the enemy hero.");
            Statistics.setGamesWonByPlayerOne(Statistics.getGamesWonByPlayerOne() + 1);
            Statistics.setGamesPlayed(Statistics.getGamesPlayed() + 1);
            return;
        }
        if (game.getPlayers().get(0).getHero().getHealth() <= 0) {
            game.setGameEnded(true);
            game.setGameEndMessage("Player two killed the enemy hero.");
            Statistics.setGamesWonByPlayerTwo(Statistics.getGamesWonByPlayerTwo() + 1);
            Statistics.setGamesPlayed(Statistics.getGamesPlayed() + 1);
        }
    }

    /**
     * Uses the hero ability on the given row.
     *
     * @param hero
     * @param player
     * @param row
     * @param err
     */
    public void useHeroAbility(final Hero hero, final Player player,
                               final int row, final Error err) {
        if (player.getMana() < hero.getMana()) {
            err.setErr(true);
            err.setMessage("Not enough mana to use hero's ability.");
            return;
        }
        if (hero.isHasAttacked()) {
            err.setErr(true);
            err.setMessage("Hero has already attacked this turn.");
            return;
        }
        switch (hero.getName()) {
            case "Lord Royce":
                if (belongsToCurrPlayer(row)) {
                    err.setErr(true);
                    err.setMessage("Selected row does not belong to the enemy.");
                    return;
                }
                Minion highestCard = table[row].get(0);
                for (var c : table[row]) {
                    if (highestCard.getAttackDamage() <= c.getAttackDamage()) {
                        highestCard = c;
                    }
                }
                highestCard.setFrozen(true);
                break;
            case "Empress Thorina":
                if (belongsToCurrPlayer(row)) {
                    err.setErr(true);
                    err.setMessage("Selected row does not belong to the enemy.");
                    return;
                }
                Minion destroyCard = table[row].get(0);
                for (var c : table[row]) {
                    if (destroyCard.getHealth() <= c.getHealth()) {
                        destroyCard = c;
                    }
                }
                destroyCard.setHealth(0);
                break;
            case "King Mudface":
                if (!belongsToCurrPlayer(row)) {
                    err.setErr(true);
                    err.setMessage("Selected row does not belong to the current player.");
                    return;
                }
                for (var c : table[row]) {
                    c.setHealth(c.getHealth() + 1);
                }
                break;
            //General Kocioraw
            default:
                if (!belongsToCurrPlayer(row)) {
                    err.setErr(true);
                    err.setMessage("Selected row does not belong to the current player.");
                    return;
                }
                for (var c : table[row]) {
                    c.setAttackDamage(c.getAttackDamage() + 1);
                }
                break;
        }
        checkHealth();
        hero.setHasAttacked(true);
        player.setMana(player.getMana() - hero.getMana());
    }

    /**
     * Checks if the given row belongs to the curent player
     *
     * @param row
     * @return
     */
    boolean belongsToCurrPlayer(final int row) {
        if (currTurn == 1) {
            if (row < 2) {
                return true;
            }
        } else {
            if (row > 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * Resets the hasAttacked variable at the end of the round
     */
    public void resetAttacks() {
        for (int i = 0; i < four; ++i) {
            ArrayList<Minion> row = table[i];
            for (var c : row) {
                c.setHasAttacked(false);
            }
        }
    }

    /**
     * Checks the health of all the card on the table, if the
     * health is below 0, the card is removed from the table
     */
    public void checkHealth() {
        for (int i = 0; i < four; ++i) {
            ArrayList<Minion> row = new ArrayList<>(table[i]);
            int j = 0;
            while (j < table[i].size()) {
                Minion card = (Minion) table[i].get(j);
                if (card.getHealth() <= 0) {
                    row.remove(card);
                }
                ++j;
            }
            table[i] = row;
        }
    }

    /**
     * Resets the frozen status of the card
     *
     * @param player
     */
    public void setFrozenFalse(final int player) {
        if (player == 0) {
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < table[i].size(); ++j) {
                    Minion card = table[i].get(j);
                    card.setFrozen(false);
                }
            }
        } else {
            for (int i = 2; i < four; ++i) {
                for (int j = 0; j < table[i].size(); ++j) {
                    Minion card = table[i].get(j);
                    card.setFrozen(false);
                }
            }
        }
    }

    /**
     * Returns the table
     *
     * @return
     */
    public ArrayList<Minion>[] getTable() {
        return table;
    }

    /**
     * Sets the table
     *
     * @param table
     */
    public void setTable(final ArrayList<Minion>[] table) {
        this.table = table;
    }

    /**
     * Returns the current playing player
     *
     * @return
     */
    public int getCurrTurn() {
        return currTurn;
    }

    /**
     * Sets the current playing parent
     *
     * @param turn
     */
    public void setCurrTurn(final int turn) {
        this.currTurn = turn;
    }

}
