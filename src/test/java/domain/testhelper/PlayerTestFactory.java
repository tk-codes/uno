package domain.testhelper;

import domain.player.Player;

public class PlayerTestFactory {
    public static Player[] createPlayers(int total) {
        Player[] players = new Player[total];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(String.format("%s", i + 1), null);
        }

        return players;
    }
}
