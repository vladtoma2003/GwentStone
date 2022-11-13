package CustomClasses;

import Cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private static void placeCard(Game game, int idx, Error err) {
        int currentPlayer = game.getTable().getCurrTurn();
        game.getTable().PlaceCard(game.getPlayers().get(currentPlayer), idx, currentPlayer, err);
    }


    public static void Command(Game game, ArrayList<ActionsInput> commands, ArrayNode output) {
        for (ActionsInput actions : commands) {
//            System.out.println(actions.getCommand().toString());
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
                    break;
                case "placeCard":
                    placeCard(game, actions.getHandIdx(), game.getErr());
                    if(game.getErr().getErr()) {
                        output.addObject().put("command", "placeCard").
                                put("handIdx", 0).
                                put("error", game.getErr().getMessage());
                    }
                    game.ResetError(game.getErr());
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
                        Collections.reverse(tabel); // solutie momentan, cand trebuie data pozitia 5-poz pt pozitia corecta
                    }
                    output.addObject().put("command", "getCardsOnTable").
                            putPOJO("output", table);
                    break;
                case "getEnvironmentCardsInHand":
                    ArrayList<Card> ret = game.getEnvInHand(game.getEnvInHand(game.getPlayers().get(actions.getPlayerIdx()).getHand()));
                    output.addObject().put("command", "getEnvironmentCardsInHand").
                            put("PlayerIdx", actions.getPlayerIdx()).
                            putPOJO("output", ret);
                    break;
                case "getCardAtPosition":
                    Card carte = game.getCardAtPosition(game.getTable(), actions.getX(), actions.getY());
                    output.addObject().put("command", "getEnvironmentCardsInHand").
                            putPOJO("output", carte);
                    break;

            }
        }
    }
}
