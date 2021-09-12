import core.Game;
import core.Player;
import core.Score;

public class Tennis {
    public static void main(String[] args) {

        Player player1 = new Player("Federer");
        Player player2 = new Player("Nadal");
        Score score = new Score();
        Game game = new Game(player1, player2, score);

        game.start();
        while (!game.isMatchEnd(player1, player2)) {
            System.out.println(score.computeScore(player1, player2));
            game.runPoint();
        }
    }
}
