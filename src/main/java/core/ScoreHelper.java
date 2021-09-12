package core;

public class ScoreHelper {

    private static final String[] scores = new String[]{"0", "15", "30", "40", "Win game"};

    public static String getScore(int numberOfPoint) {
        return scores[numberOfPoint];
    }

}
