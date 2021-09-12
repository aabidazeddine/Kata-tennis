package core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
    Player player1 = null;
    Player player2 = null;
    Score score = new Score();
    Game game = null;

    @BeforeEach
    public void init() {
        player1 = new Player("Federer");
        player2 = new Player("Nadal");
        game = new Game(player1, player2, score);
    }

    @Test
    void when_game_starts_score_should_be_0_for_both_players() {
        game.start();

        assertThat(player1.getScore()).isEqualTo("0");
        assertThat(player2.getScore()).isEqualTo("0");
    }

    @Test
    void when_a_player_win_1_point_the_Game_score_should_be_15() {
        player1.winPoint();
        assertThat(player1.getScore()).isEqualTo("15");
    }

    @Test
    void when_a_player_win_2_points_the_Game_score_should_be_30() {
        player1.winPoint();
        player1.winPoint();

        assertThat(player1.getScore()).isEqualTo("30");
    }

    @Test
    void when_a_player_win_3_points_the_Game_score_should_be_40() {
        player1.winPoint();
        player1.winPoint();
        player1.winPoint();

        assertThat(player1.getScore()).isEqualTo("40");
    }

    @Test
    void when_a_player_win_4_points_the_Game_score_should_be_Win_game() {
        player1.winPoint();
        player1.winPoint();
        player1.winPoint();
        player1.winPoint();

        assertThat(player1.getScore()).isEqualTo("Win game");
    }

    @Test
    void if_the_2_players_reach_the_score_40_the_DEUCE_rule_is_activated() {
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player2));

        assertThat(score.isDeuceActivated()).isTrue();
    }

    @Test
    void if_the_2_players_do_not_reach_the_score_40_the_DEUCE_rule_is_not_activated() {
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 2).forEach(i -> game.playerWinsPoint(player2));

        assertThat(score.isDeuceActivated()).isFalse();
    }

    @Test
    void if_the_score_is_DEUCE_the_player_who_win_the_point_take_the_ADVANTAGE() {
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player2));

        game.playerWinsPoint(player1);

        assertThat(player1.hasAdvantage()).isTrue();
        assertThat(player2.hasAdvantage()).isFalse();

    }

    @Test
    void if_the_player_who_has_the_ADVANTAGE_win_the_point_he_win_the_game() {
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player2));

        game.playerWinsPoint(player1);
        game.playerWinsPoint(player1);

        assertThat(player1.hasWonTheGame()).isTrue();
        assertThat(player2.hasWonTheGame()).isFalse();
    }

    @Test
    void if_the_player_who_has_the_ADVANTAGE_loose_the_point_the_score_is_DEUCE() {
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 3).forEach(i -> game.playerWinsPoint(player2));

        game.playerWinsPoint(player1);
        game.playerWinsPoint(player2);

        assertThat(player1.hasAdvantage()).isFalse();
        assertThat(player2.hasAdvantage()).isFalse();
        assertThat(player1.hasWonTheGame()).isFalse();
        assertThat(player2.hasWonTheGame()).isFalse();
        assertThat(score.computeScore(player1, player2)).isEqualTo("DEUCE");

    }

    @Test
    void the_set_starts_with_a_score_0_Game_for_each_player() {
        game.start();

        assertThat(player1.getNumberOfGame()).isEqualTo(0);
        assertThat(player2.getNumberOfGame()).isEqualTo(0);
    }

    @Test
    void each_time_a_player_win_a_Game_the_Set_score_changes() {
        IntStream.range(0, 4).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 1).forEach(i -> game.playerWinsPoint(player2));

        assertThat(player1.hasWonTheGame()).isTrue();
        assertThat(player1.getNumberOfGame()).isEqualTo(1);

        IntStream.range(0, 2).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 4).forEach(i -> game.playerWinsPoint(player2));

        assertThat(player2.hasWonTheGame()).isTrue();
        assertThat(player2.getNumberOfGame()).isEqualTo(1);
        IntStream.range(0, 2).forEach(i -> game.playerWinsPoint(player1));
        IntStream.range(0, 4).forEach(i -> game.playerWinsPoint(player2));

        assertThat(player2.hasWonTheGame()).isTrue();
        assertThat(player2.getNumberOfGame()).isEqualTo(2);
    }

    @Test
    void If_a_player_reach_the_Set_score_of_6_and_the_other_player_4_or_lower_the_player_win_the_Set() {
        IntStream.range(0, 2).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player1));


        assertThat(player1.getNumberOfGame()).isEqualTo(6);
        assertThat(score.computeWinnerOfMatch(player1, player2)).isEqualTo("Federer");
    }

    @Test
    void If_a_score_is_6_5_a_new_Game_must_be_played_and_the_first_player_who_reach_7_wins_the_match() {
        IntStream.range(0, 28).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 20).forEach(i -> game.playerWinsPoint(player1));

        assertThat(player2.getNumberOfGame()).isEqualTo(7);
        assertThat(score.computeWinnerOfMatch(player1, player2)).isEqualTo("Nadal");
    }

    @Test
    void if_the_2_players_reach_the_score_of_6_Games_the_tie_Break_rule_is_activate() {
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player1));

        assertThat(tieBreakRule.tieBreakActivate(player1, player2)).isEqualTo(true);
    }

    @Test
    void each_time_a_player_win_a_point_the_score_changes_before_tie_break_activate() {
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 32).forEach(i -> game.playerWinsPoint(player1));

        game.playerWinsPoint(player2);
        game.playerWinsPoint(player2);

        assertThat(tieBreakRule.tieBreakActivate(player1, player2)).isEqualTo(true);
        assertThat(player1.getNumberOfPoints()).isEqualTo(8);
        assertThat(player2.getNumberOfPoints()).isEqualTo(2);
    }

    @Test
    void tie_break_ends_as_soon_as_a_player_gets_a_least_6_points_and_gets_2_points_more_than_other_player() {
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 31).forEach(i -> game.playerWinsPoint(player1));

        game.playerWinsPoint(player2);

        assertThat(tieBreakRule.tieBreakActivate(player1, player2)).isEqualTo(true);
        assertThat(player1.getNumberOfPoints()).isEqualTo(7);
        assertThat(player2.getNumberOfPoints()).isEqualTo(1);
        assertThat(tieBreakRule.endOfTieBreak(player1, player2)).isEqualTo(true);
    }

    @Test
    void the_player_who_wins_the_tie_break_wins_the_Set_and_the_match() {
        IntStream.range(0, 24).forEach(i -> game.playerWinsPoint(player2));
        IntStream.range(0, 31).forEach(i -> game.playerWinsPoint(player1));

        game.playerWinsPoint(player2);

        assertThat(tieBreakRule.tieBreakActivate(player1, player2)).isEqualTo(true);
        assertThat(player1.getNumberOfPoints()).isEqualTo(7);
        assertThat(player2.getNumberOfPoints()).isEqualTo(1);
        assertThat(tieBreakRule.endOfTieBreak(player1, player2)).isEqualTo(true);
        assertThat(score.computeWinnerOfMatch(player1, player2)).isEqualTo("Federer");
    }
}
