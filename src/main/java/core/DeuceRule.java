package core;

public class DeuceRule {

    public static final int DEUCE_LIMIT = 3;


    static boolean isDeuceLimit(Player player1, Player player2) {
        return player1.getNumberOfPoints() == DEUCE_LIMIT && player2.getNumberOfPoints() == DEUCE_LIMIT;
    }

    static Player getPlayerWithHigherNumberOfPoints(Player player1, Player player2) {
        return player1.getNumberOfPoints() > player2.getNumberOfPoints() ? player1 : player2;
    }

    static Player getPlayerWithSmallerNumberOfPoints(Player player1, Player player2) {
        return player1.getNumberOfPoints() < player2.getNumberOfPoints() ? player1 : player2;
    }
}
