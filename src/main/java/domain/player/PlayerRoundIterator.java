package domain.player;

import java.util.Arrays;
import java.util.stream.Stream;

public class PlayerRoundIterator {
    private final Player[] players;
    private int current = 0;
    private Direction direction = Direction.CLOCKWISE;

    public PlayerRoundIterator(Player[] players) {
        this.players = players;
    }

    public Stream<Player> stream() {
        return Arrays.stream(players);
    }

    public Player getCurrentPlayer() {
        return players[current];
    }

    public void reverseDirection() {
        direction = Direction.COUNTER_CLOCK_WISE;
    }

    public Player next() {
        current = getNextIndex();
        return getCurrentPlayer();
    }

    private int getNextIndex() {
        var increment = direction == Direction.CLOCKWISE ? 1 : -1;
        return (players.length + current + increment) % 3;
    }
}
