package core;

public class tieBreakRule {
    public static boolean tieBreakActivate(Player player1, Player player2) {
        return player1.getNumberOfGame() >= 6 && player2.getNumberOfGame() >= 6;
    }

    public static boolean endOfTieBreak(Player player1, Player player2) {
        boolean endOfTieBreak = false;
        if (player1.getNumberOfPoints() > 6 || player2.getNumberOfPoints() > 6) {
            if (Math.abs(player1.getNumberOfPoints() - player2.getNumberOfPoints()) >= 2) {
                endOfTieBreak = true;
            } else if (Math.abs(player2.getNumberOfPoints() - player1.getNumberOfPoints()) >= 2) {
                endOfTieBreak = true;
            }
        }
        return endOfTieBreak;
    }

    public static Player hasWonTieBreak(Player player1, Player player2) {
        Player playerWinner = new Player();
        if (tieBreakActivate(player1, player2) && endOfTieBreak(player1, player2)) {
            if (player1.getNumberOfPoints() > player2.getNumberOfPoints()) {
                playerWinner = player1;
                player1.setWonTheMatch(true);
                System.out.println(player1.getPlayerName() + " won the tie-break and Match");
            } else {
                playerWinner = player2;
                player2.setWonTheMatch(true);
                System.out.println(player2.getPlayerName() + " won the tie-break and Match");
            }
        }
        return playerWinner;
    }

}
