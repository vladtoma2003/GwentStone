package CustomClasses;

public class Statistics {
    private static int gamesWonByPlayerOne = 0;
    private static int gamesWonByPlayerTwo = 0;

    private static int gamesPlayed = 0;

    public static int getGamesWonByPlayerOne() {
        return gamesWonByPlayerOne;
    }

    public static void setGamesWonByPlayerOne(int gamesWonByPlayerOne) {
        Statistics.gamesWonByPlayerOne = gamesWonByPlayerOne;
    }

    public static int getGamesWonByPlayerTwo() {
        return gamesWonByPlayerTwo;
    }

    public static void setGamesWonByPlayerTwo(int gamesWonByPlayerTwo) {
        Statistics.gamesWonByPlayerTwo = gamesWonByPlayerTwo;
    }

    public static int getGamesPlayed() {
        return gamesPlayed;
    }

    public static void setGamesPlayed(int gamesPlayed) {
        Statistics.gamesPlayed = gamesPlayed;
    }
}
