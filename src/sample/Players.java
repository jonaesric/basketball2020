package sample;

import java.util.List;

public class Players {

    public playerList homePlayers;
    public playerList awayPlayers;

    public Players(playerList homePlayers, playerList awayPlayers) {
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
    }

    static class playerList {

        String name;
        List<player> rooster;

        playerList(String name, List<player> rooster) {
            this.name = name;
            this.rooster = rooster;
        }

    }
}
