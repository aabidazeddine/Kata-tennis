package core;

import java.util.Random;

public class Game {


    private final Player player1;
    private final Player player2;
    private final Score score;


    public Game(Player player1, Player player2, Score score) {
        this.player1 = player1;
        this.player2 = player2;
        this.score = score;
    }

    public void start() {
        player1.resetPoints();
        player2.resetPoints();
    }

    public void playerWinsPoint(Player player) {
        player.winPoint();
        score.compute(player1, player2);
    }

    public void runPoint() {
        Random r = new Random();
        Player winnerPlayer = r.ints(0, 2).findFirst().getAsInt() == 0 ? player1 : player2;
        System.out.println("Point for " + winnerPlayer.getPlayerName());
        playerWinsPoint(winnerPlayer);
    }

    public boolean isMatchEnd(Player player1, Player player2) {
        return player1.isWonTheMatch() || player2.isWonTheMatch();
    }
}
