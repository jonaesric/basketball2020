
package sample;


public class GameState {

    Long timePassed;
    String score;
    String homeFouls;
    String awayFouls;
    String homeTimeouts;
    String awayTimeouts;
    String period;

    GameState(Game g) {
        this.period = g.PERIOD.get();
        this.timePassed = g.timeMillis.get();
        this.score = g.SCORE.get();
        this.homeFouls = g.HOME_FOULS.get();
        this.awayFouls = g.AWAY_FOULS.get();
        this.homeTimeouts = g.HOME_TIMEOUTS.get();
        this.awayTimeouts = g.AWAY_TIMEOUTS.get();

    }

}
