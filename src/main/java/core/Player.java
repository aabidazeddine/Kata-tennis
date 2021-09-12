package core;

public class Player {

    private String playerName;
    private int numberOfPoints;
    private int numberOfGame;
    private boolean advantage;
    private boolean wonTheGame;
    private boolean wonTheMatch;

    public Player(String name) {
        this.playerName = name;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isWonTheMatch() {
        return wonTheMatch;
    }

    public void setWonTheMatch(boolean wonTheMatch) {
        this.wonTheMatch = wonTheMatch;
    }

    public String getScore() {
        return ScoreHelper.getScore(numberOfPoints);
    }

    public void resetPoints() {
        this.numberOfPoints = 0;
    }

    public void winPoint() {
        this.numberOfPoints++;
    }

    public int getNumberOfPoints() {
        return this.numberOfPoints;
    }

    public int getNumberOfGame() {
        return this.numberOfGame;
    }

    public void setNumberOfGame(int numberOfGame) {
        this.numberOfGame = numberOfGame;
    }

    public boolean hasAdvantage() {
        return this.advantage;
    }

    public void setAdvantage(boolean advantage) {
        this.advantage = advantage;
    }

    public boolean hasWonTheGame() {
        return this.wonTheGame;
    }

    public void setWonTheGame(boolean wonTheGame) {
        this.wonTheGame = wonTheGame;
    }

}
