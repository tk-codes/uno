package domain.testhelper;

import domain.player.HandCardList;
import domain.player.Player;

public class PlayerTestFactory {
    public static Player[] createPlayers(int total) {
        Player[] players = new Player[total];

        for (int i = 0; i < players.length; i++) {
            var handCards = new HandCardList();
            players[i] = new Player(String.format("%s", i + 1), handCards);
        }

        return players;
    }
}
