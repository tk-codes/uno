package domain.game;

import domain.player.Player;
import domain.player.PlayerRoundIterator;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayersIterator {

    private final Player[] players = PlayerTestFactory.createPlayers(3);
    private PlayerRoundIterator playerIterator;

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
        playerIterator.reverseDirection();

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
