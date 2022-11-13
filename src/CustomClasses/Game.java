package CustomClasses;

import Cards.*;
import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import static java.util.Collections.shuffle;

public class Game {
    private Table table;
    private ArrayList<Player> players;
    private int turnsThisGame = 0;
    private int round = 1;
    private Error err;

    public void PrintHand() {
        String ceva1 = players.get(0).getHand().toString();
        String ceva2 = players.get(1).getHand().toString();

        System.out.println("Hand1: " + ceva1);
        System.out.println("Hand2: " + ceva2);
    }

    public Game(Input inputData, int curr_game) {
        GameInput input = inputData.getGames().get(curr_game);

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
        p1.setClosestRow(3);
        p2.setClosestRow(0);

        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        err = new Error();

        table = new Table(input.getStartGame().getStartingPlayer());

    }

    public void ResetError(Error err) {
        err.setMessage(null);
        err.setErr(false);
    }

    public void GamePrep(GameInput input) {
        shuffle(players.get(0).getDeck(), new Random(input.getStartGame().getShuffleSeed()));
        shuffle(players.get(1).getDeck(), new Random(input.getStartGame().getShuffleSeed()));

        PickUpCard(players, 0);
        PickUpCard(players, 1);

        table.setCurrTurn(input.getStartGame().getStartingPlayer() - 1);
//        System.out.println("Starting turn:" + input.getStartGame().getStartingPlayer());
    }
    public void PickUpCard(ArrayList<Player> players, int idx) {
        var deck = new ArrayList<>(players.get(idx).getDeck());
        getPlayers().get(idx).getHand().add(deck.get(0));
        getPlayers().get(idx).getDeck().remove(0);
    }

    public void NewRound() {
        ++round;
        if(round < 10) {
            players.get(0).setMana(players.get(0).getMana() + round);
            players.get(1).setMana(players.get(1).getMana() + round);
        } else {
            players.get(0).setMana(players.get(0).getMana() + 10);
            players.get(1).setMana(players.get(1).getMana() + 10);
        }
        PickUpCard(players, 0);
        PickUpCard(players, 1);
//        System.out.println("Runda " + round);
    }

    public ArrayList<Card> getEnvInHand(ArrayList<Card> hand) {

        ArrayList<Card> arr = new ArrayList<>(hand);
        for(var card:hand) {
            if (!(card.getName().equals("Winterfell") ||
                    card.getName().equals("Firestorm") ||
                    card.getName().equals("Heart Hound"))) {
                arr.remove(card);
            }
        }

        return arr;
    }

    public Card getCardAtPosition (Table table, int x, int y) {
        int pos = 4-x;
        int r = 3-y;

        ArrayList<Card> row = table.getRow(y);
        Card carte = new Card(row.get(x));

        return carte;
    }

    public void switchTurns() {
        ++turnsThisGame;
        table.setCurrTurn((table.getCurrTurn()+1)%2);
    }


    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getTurnsThisGame() {
        return turnsThisGame;
    }

    public int getRound() {
        return round;
    }

    public void setTurnsThisGame(int turnsThisGame) {
        this.turnsThisGame = turnsThisGame;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public Error getErr() {
        return err;
    }

    public void setErr(Error err) {
        this.err = err;
    }
}
