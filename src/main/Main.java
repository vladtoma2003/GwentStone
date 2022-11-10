package main;

import checker.Checker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import checker.CheckerConstants;
import fileio.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.util.Collections.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH + filePath1),
                Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        //TODO add here the entry point to your implementation

        GameInput input = inputData.getGames().get(0);

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
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);

        shuffle(players.get(0).getDeck(), new Random(input.getStartGame().getShuffleSeed()));
        shuffle(players.get(1).getDeck(), new Random(input.getStartGame().getShuffleSeed()));

        players.get(0).PickUpCard(players.get(0).getDeck());
        players.get(1).PickUpCard(players.get(1).getDeck());


        if (input.getStartGame().getStartingPlayer() == 1) {
            players.get(0).setTurn(true);
        } else {
            players.get(1).setTurn(true);
        }

        for (var actions : inputData.getGames().get(0).getActions()) {
            switch (actions.getCommand()) {
                case "getPlayerDeck":
                    var curr_deck = players.get(actions.getPlayerIdx() - 1).getDeck();
                    output.addObject().put("command", "getPlayerDeck").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", curr_deck);
                    break;
                case "getPlayerHero":
                    var curr_hero = players.get(actions.getPlayerIdx() - 1).getHero();
                    output.addObject().put("command", "getPlayerHero").
                            put("playerIdx", actions.getPlayerIdx()).
                            putPOJO("output", curr_hero);
                    break;
                case "getPlayerTurn":
                    var currTurn = players.get(0).isTurn() ? 1 : 2;
                    output.addObject().put("command", "getPlayerTurn").
                            put("output", currTurn);
                    break;

            }
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
