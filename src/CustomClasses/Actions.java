package CustomClasses;

import Cards.Card;
import Cards.Environment;
import Cards.Minion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.Coordinates;
import fileio.GameInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Actions {

    private static void endTurn(Game game) {
        int currP = game.getTable().getCurrTurn();
        game.switchTurns();
        if(game.getTurnsThisGame() % 2 == 0) {
            game.NewRound();
        }

        if(currP == 0) {
            game.getTable().setFrozenFalse(1);
            game.getTable().resetAttacks();
        }

    }

    private static void placeCard(Game game, int idx, Error err) {
        int currentPlayer = game.getTable().getCurrTurn();
        game.getTable().PlaceCard(game.getPlayers().get(currentPlayer), idx, currentPlayer, err);
    }


    public static void Command(Game game, ArrayList<ActionsInput> commands, ArrayNode output) {
        for (ActionsInput actions : commands) {
            switch (actions.getCommand()) {
                case "getPlayerDeck":
                    var deck = game.getPlayers().get(actions.getPlayerIdx() - 1).getDeck();
                    var curr_deck = new ArrayList<Card>(deck);
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
                    var hand = new ArrayList<>();
                    for(var c:curr_hand) {
                        if (c.getName().equals("Winterfell") ||
                                c.getName().equals("Firestorm") ||
                                c.getName().equals("Heart Hound")) {
                            Environment env = new Environment(c);
                            hand.add(env);
                        } else {
                            Minion mini = (Minion) c;
                            Minion newMini = new Minion(mini);
                            hand.add(newMini);
                        }
                    }
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
                    output.addObject().put("command", "getCardsOnTable").
                            putPOJO("output", table);
                    break;
                case "getEnvironmentCardsInHand":
                    ArrayList<Card> ceva = game.getEnvInHand(game.getPlayers().get(actions.getPlayerIdx() - 1).getHand());
                    ArrayList<Card> ret = new ArrayList<>(ceva);
                    output.addObject().put("command", "getEnvironmentCardsInHand").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", ret);
                    break;
                case "getCardAtPosition":
                    Minion carte = game.getTable().getCardAtPosition(actions.getX(), actions.getY(), game.getErr());
                    if(carte == null){
                        output.addObject().put("command", "getCardAtPosition").
                                put("x", actions.getX()).
                                put("y", actions.getY()).
                                put("output", game.getErr().getMessage());
                    } else {
                        output.addObject().put("command", "getCardAtPosition").
                                put("x", actions.getX()).
                                put("y", actions.getY()).
                                putPOJO("output", carte);
                    }
                    game.ResetError(game.getErr());
                    break;
                case "useEnvironmentCard":
                    game.getTable().useEnvCard(game.getPlayers().get(game.getTable().getCurrTurn()),
                            actions.getHandIdx(), actions.getAffectedRow(), game.getErr());
                    if(game.getErr().getErr()) {
                        output.addObject().put("command", "useEnvironmentCard").
                                put("handIdx", actions.getHandIdx()).
                                put("affectedRow", actions.getAffectedRow()).
                                put("error", game.getErr().getMessage());
                    }
                    game.ResetError(game.getErr());
                    break;
                case "getFrozenCardsOnTable":
                    var arr = game.getTable().getFrozenCards();
                    ArrayList<Minion> altceva = new ArrayList<>(arr);
                    output.addObject().put("command", "getFrozenCardsOnTable").
                            putPOJO("output", altceva);
                    break;
                case "cardUsesAttack":
                    var attacker = actions.getCardAttacker();
                    var attacked = actions.getCardAttacked();
                    game.getTable().Attack(attacker.getX(), attacker.getY(), attacked.getX(), attacked.getY(), game.getErr());
                    if(game.getErr().getErr()) {
                        output.addObject().put("command", "cardUsesAttack").
                                putPOJO("cardAttacker", attacker).
                                putPOJO("cardAttacked", attacked).
                                put("error", game.getErr().getMessage());
                    }
                    game.ResetError(game.getErr());
                    break;

            }
        }
    }
}
