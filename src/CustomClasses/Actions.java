package CustomClasses;

import Cards.Card;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Actions {

    private static void endTurn(Game game) {
        game.switchTurns();
        if(game.getTurnsThisGame() % 2 == 0) {
            game.NewRound();
        }

    }

    private static void placeCard(Game game, int idx) {
        int currentPlayer = game.getTable().getCurrTurn();
        game.getTable().PlaceCard(game.getPlayers().get(currentPlayer), idx, currentPlayer);
    }


    public static void Command(Game game, ArrayList<ActionsInput> commands, ArrayNode output) {
        for (ActionsInput actions : commands) {
            System.out.println(actions.getCommand().toString());
            switch (actions.getCommand()) {
                case "getPlayerDeck":
                    var curr_deck = game.getPlayers().get(actions.getPlayerIdx() - 1).getDeck();
                    output.addObject().put("command", "getPlayerDeck").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", curr_deck);
                    break;
                case "getPlayerHero":
                    var curr_hero = game.getPlayers().get(actions.getPlayerIdx() - 1).getHero();
                    output.addObject().put("command", "getPlayerHero").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", curr_hero);
                    break;
                case "getPlayerTurn":
                    var currTurn = game.getTable().getCurrTurn();
                    output.addObject().put("command", "getPlayerTurn").
                            put("output", currTurn+1);
                    break;
                case "endPlayerTurn":
                    endTurn(game);
                    System.out.println(game.getTurnsThisGame());
                    break;
                case "placeCard":
                    placeCard(game, actions.getHandIdx());
                    break;
                case "getCardsInHand":
                    var curr_hand = game.getPlayers().get(actions.getPlayerIdx() - 1).getHand();
                    var hand = new ArrayList<>(curr_hand);
                    output.addObject().put("command", "getCardsInHand").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", hand);
                    break;
                case "getPlayerMana":
                    var mana = game.getPlayers().get(actions.getPlayerIdx() - 1).getMana();
                    output.addObject().put("command", "getPlayerMana").
                            put("playerIdx", actions.getPlayerIdx()).
                            put("output", mana);
                    break;
                case "getCardsOnTable":
                    var table = game.getTable().getTable();
                    for(var tabel:table) {
                        Collections.reverse(tabel); // solutie momentan
                    }

                    output.addObject().put("command", "getCardsOnTable").
                            putPOJO("output", table);

            }
        }

    }
}
