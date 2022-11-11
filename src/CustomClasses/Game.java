package CustomClasses;

import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Collections.shuffle;

public class Game {
    private Table table;
    private ArrayList<Player> players;
    private static int turnsThisGame = 0;
    private static int round = 1;

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
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        table = new Table(input.getStartGame().getStartingPlayer());

    }

    public void GamePrep(GameInput input) {
        shuffle(players.get(0).getDeck(), new Random(input.getStartGame().getShuffleSeed()));
        shuffle(players.get(1).getDeck(), new Random(input.getStartGame().getShuffleSeed()));

        players.get(0).PickUpCard(players.get(0).getDeck());
        players.get(1).PickUpCard(players.get(1).getDeck());

        table.setCurr_turn(input.getStartGame().getStartingPlayer());
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

    public static int getTurnsThisGame() {
        return turnsThisGame;
    }

    public static int getRound() {
        return round;
    }

}
