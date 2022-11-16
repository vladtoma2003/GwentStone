package classes.myClasses;

import cards.Card;
import cards.Hero;
import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

public class Game {
    private Table table;
    private ArrayList<Player> players;
    private int turnsThisGame = 0;
    private int round = 1;
    private Error err;
    private boolean gameEnded = false;
    private String gameEndMessage;
    private final int p1ClosestRow = 3;
    private final int p2ClosestRow = 0;
    private final int maxRound = 10;

    public Game(final Input inputData, final int currGame) {
        GameInput input = inputData.getGames().get(currGame);

        int deck1Idx = input.getStartGame().getPlayerOneDeckIdx();
        int deck2Idx = input.getStartGame().getPlayerTwoDeckIdx();

        ArrayList<CardInput> deck1 = inputData.getPlayerOneDecks().getDecks().get(deck1Idx);
        ArrayList<CardInput> deck2 = inputData.getPlayerTwoDecks().getDecks().get(deck2Idx);

        DeckInit p1deck = new DeckInit(deck1);
        DeckInit p2deck = new DeckInit(deck2);

        CardInput hero1 = input.getStartGame().getPlayerOneHero();
        CardInput hero2 = input.getStartGame().getPlayerTwoHero();

        Hero herop1 = new Hero(hero1);
        Hero herop2 = new Hero(hero2);

        Player p1 = new Player(p1deck.getDeck(), herop1, 1);
        Player p2 = new Player(p2deck.getDeck(), herop2, 2);
        p1.setClosestRow(p1ClosestRow);
        p2.setClosestRow(p2ClosestRow);

        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        err = new Error();

        table = new Table(input.getStartGame().getStartingPlayer());

    }

    public void resetError(final Error err) {
        err.setMessage(null);
        err.setErr(false);
    }

    public void gamePrep(final GameInput input) {
        shuffle(players.get(0).getDeck(), new Random(input.getStartGame().getShuffleSeed()));
        shuffle(players.get(1).getDeck(), new Random(input.getStartGame().getShuffleSeed()));

        players.get(0).getHand().add(pickUpCard(players, 0));
        players.get(1).getHand().add(pickUpCard(players, 1));

        table.setCurrTurn(input.getStartGame().getStartingPlayer() - 1);
    }

    public Card pickUpCard(final ArrayList<Player> players, final int idx) {
        var deck = new ArrayList<>(players.get(idx).getDeck());
        var card = deck.get(0);
        getPlayers().get(idx).getDeck().remove(0);
        return card;
    }

    public void newRound() {
        ++round;
        if (round < maxRound) {
            players.get(0).setMana(players.get(0).getMana() + round);
            players.get(1).setMana(players.get(1).getMana() + round);
        } else {
            players.get(0).setMana(players.get(0).getMana() + maxRound);
            players.get(1).setMana(players.get(1).getMana() + maxRound);
        }
        if (players.get(0).getDeck().size() > 0) {
            players.get(0).getHand().add(pickUpCard(players, 0));
        }
        if (players.get(1).getDeck().size() > 0) {
            players.get(1).getHand().add(pickUpCard(players, 1));
        }

    }

    public ArrayList<Card> getEnvInHand(final ArrayList<Card> hand) {
        ArrayList<Card> arr = new ArrayList<>();
        for (var card : hand) {
            if ((card.getName().equals("Winterfell")
                    || card.getName().equals("Firestorm")
                    || card.getName().equals("Heart Hound"))) {
                Card carte = new Card(card);
                arr.add(carte);
            }
        }

        return arr;
    }

    public void switchTurns() {
        ++turnsThisGame;
        table.setCurrTurn((table.getCurrTurn() + 1) % 2);
    }


    public Table getTable() {
        return table;
    }

    public void setTable(final Table table) {
        this.table = table;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final ArrayList<Player> players) {
        this.players = players;
    }

    public int getTurnsThisGame() {
        return turnsThisGame;
    }

    public int getRound() {
        return round;
    }

    public void setTurnsThisGame(final int turnsThisGame) {
        this.turnsThisGame = turnsThisGame;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public Error getErr() {
        return err;
    }

    public void setErr(final Error err) {
        this.err = err;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(final boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public String getGameEndMessage() {
        return gameEndMessage;
    }

    public void setGameEndMessage(final String gameEndMessage) {
        this.gameEndMessage = gameEndMessage;
    }
}
