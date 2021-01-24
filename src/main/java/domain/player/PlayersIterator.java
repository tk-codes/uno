package domain.player;

import java.util.Arrays;
import java.util.stream.Stream;

public class PlayersIterator {
    private final Player[] players;

    public PlayersIterator(Player[] players){
        this.players = players;
    }

    public int size(){
        return players.length;
    }

    public Stream<Player> stream() {
        return Arrays.stream(players);
    }
}
