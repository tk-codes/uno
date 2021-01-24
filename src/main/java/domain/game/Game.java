package domain.game;

import domain.player.Player;
import domain.player.PlayerRoundIterator;

import java.util.stream.Stream;

public class Game {
    private final DrawPile drawPile;
    private final PlayerRoundIterator players;

    public Game(DrawPile drawPile, PlayerRoundIterator players) {
        this.drawPile = drawPile;
        this.players = players;
    }

    public Stream<Player> getPlayers() {
        return players.stream();
    }
}
