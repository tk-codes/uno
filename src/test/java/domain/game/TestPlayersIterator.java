package domain.game;

import domain.player.Player;
import domain.player.PlayerRoundIterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayersIterator {

    private static Player[] players = new Player[3];
    private PlayerRoundIterator playerIterator;

    @BeforeAll
    static void beforeAll() {
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(String.format("%s", i + 1), null);
        }
    }

    @BeforeEach
    void setUp() {
        playerIterator = new PlayerRoundIterator(players);
    }

    @Test
    public void WhenInitialized_FirstPlayerShouldBeCurrent() {
        var current = playerIterator.getCurrentPlayer();

        assertPlayer(current, "1");
    }

    @Test
    public void WhenClockwise_ShouldHaveAscendingOrder() {
        var current = playerIterator.next();
        assertPlayer(current, "2");

        current = playerIterator.next();
        assertPlayer(current, "3");

        current = playerIterator.next();
        assertPlayer(current, "1");
    }

    @Test
    public void WhenReversed_ShouldHaveDescendingOrder() {
        playerIterator.reverse();

        var current = playerIterator.getCurrentPlayer();
        assertPlayer(current, "1");

        current = playerIterator.next();
        assertPlayer(current, "3");

        current = playerIterator.next();
        assertPlayer(current, "2");

        current = playerIterator.next();
        assertPlayer(current, "1");
    }

    private void assertPlayer(Player player, String expectedName){
        assertEquals(expectedName, player.getName());
    }
}
