package CustomClasses;

import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class Actions {

    private void endTurn(Game game) {

        game.getTable().switchTurns();

    }


    public static void Command(Game game, ArrayList<ActionsInput> commands, ArrayNode output) {
        for (ActionsInput actions : commands) {
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
                    var currTurn = game.getTable().getCurr_turn();
                    output.addObject().put("command", "getPlayerTurn").
                            put("output", currTurn);
                    break;
                case "endPlayerTurn":
                    break;

            }
        }

    }
}
