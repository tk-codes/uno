package domain.game;

import domain.player.Player;
import domain.player.PlayersIterator;

import java.util.stream.Stream;

public class Game {
    private final DrawPile drawPile;
    private final PlayersIterator players;

    public Game(DrawPile drawPile, PlayersIterator players) {
        this.drawPile = drawPile;
        this.players = players;
    }

    public Stream<Player> getPlayers() {
        return players.stream();
    }
}
