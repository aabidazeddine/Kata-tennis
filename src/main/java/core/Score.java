package core;

public class Score {

    private boolean deuceActivated;

    public void compute(Player player1, Player player2) {
        if (player1.getNumberOfPoints() > 3 || player2.getNumberOfPoints() > 3) {
            computeClassicWin(player1, player2);
        }
        if (onOfThePlayerHasAdvantage(player1, player2)) {
            computeAndSetAdvantage(player1, player2);
        }
        if (DeuceRule.isDeuceLimit(player1, player2)) {
            activateDeuce();
        }
        if (isDeuceActivated()) {
            DeuceRule.getPlayerWithHigherNumberOfPoints(player1, player2).setAdvantage(true);
            DeuceRule.getPlayerWithSmallerNumberOfPoints(player1, player2).setAdvantage(false);
        }

        computeWinnerOfMatch(player1, player2);
    }

    public String computeScore(Player player1, Player player2) {
        String scoreExact;

        if (player1.hasAdvantage()) {
            scoreExact = "Advantage for " + player1.getPlayerName();
        } else if (player2.hasAdvantage()) {
            scoreExact = "Advantage for " + player2.getPlayerName();
        } else if (isDeuceActivated()) {
            scoreExact = "DEUCE";
        } else if (tieBreakRule.tieBreakActivate(player1, player2)) {
            scoreExact = player1.getNumberOfPoints() + "-" + player2.getNumberOfPoints();
        } else {
            scoreExact = player1.getScore() + "-" + player2.getScore();
        }

        return scoreExact;
    }

    private void computeClassicWin(Player player1, Player player2) {
        if (Math.abs(player1.getNumberOfPoints() - player2.getNumberOfPoints()) >= 2 && !tieBreakRule.tieBreakActivate(player1, player2)) {
            if (player1.getNumberOfPoints() > player2.getNumberOfPoints()) {
                winGame(player1);
            } else {
                winGame(player2);
            }
            System.out.println("Set Score : " + player1.getNumberOfGame() + "-" + player2.getNumberOfGame());
            resetPlayersPoints(player1, player2);
        }
    }

    private void computeAndSetAdvantage(Player player1, Player player2) {
        if (player1.hasAdvantage() && player1.getNumberOfPoints() > player2.getNumberOfPoints()) {
            winGame(player1);
            System.out.println("Set Score : " + player1.getNumberOfGame() + "-" + player2.getNumberOfGame());
            resetPlayersPoints(player1, player2);
        } else if (player2.hasAdvantage() && player2.getNumberOfPoints() > player1.getNumberOfPoints()) {
            winGame(player2);
            System.out.println("Set Score : " + player1.getNumberOfGame() + "-" + player2.getNumberOfGame());
            resetPlayersPoints(player1, player2);
        } else
            player1.setAdvantage(false);
        player2.setAdvantage(false);
    }

    private void resetPlayersPoints(Player player1, Player player2) {
        player1.resetPoints();
        player2.resetPoints();
    }

    private boolean onOfThePlayerHasAdvantage(Player player1, Player player2) {
        return player1.hasAdvantage() || player2.hasAdvantage();
    }

    private void winGame(Player player) {
        player.setWonTheGame(true);
        player.setAdvantage(false);
        setDeuceActivated(false);
        System.out.println(player.getPlayerName() + " won game");
        player.setNumberOfGame(player.getNumberOfGame() + 1);
    }

    public Player hasWonSet(Player player1, Player player2) {
        Player playerWinner = new Player();
        if ((player1.getNumberOfGame() == 6 && player2.getNumberOfGame() < 5)
                || (player1.getNumberOfGame() == 7 && player2.getNumberOfGame() >= 5)) {
            player1.setWonTheMatch(true);
            playerWinner = player1;
            System.out.println(player1.getPlayerName() + " won the set and Match");

        }
        if ((player2.getNumberOfGame() == 6 && player1.getNumberOfGame() < 5)
                || player2.getNumberOfGame() == 7 && player1.getNumberOfGame() >= 5) {
            playerWinner = player2;
            player2.setWonTheMatch(true);
            System.out.println(player2.getPlayerName() + " won the set and Match");
        }
        return playerWinner;
    }


    public String computeWinnerOfMatch(Player player1, Player player2) {
        Player winnerPlayer;
        if (tieBreakRule.tieBreakActivate(player1, player2)) {
            winnerPlayer = tieBreakRule.hasWonTieBreak(player1, player2);
        } else {
            winnerPlayer = hasWonSet(player1, player2);
        }

        return winnerPlayer.getPlayerName();
    }

    private void activateDeuce() {
        this.deuceActivated = true;
    }

    public boolean isDeuceActivated() {
        return deuceActivated;
    }

    public void setDeuceActivated(boolean deuceActivated) {
        this.deuceActivated = deuceActivated;
    }
}
