package domain.player;

import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayersIterator {

    private final Player[] players = PlayerTestFactory.createPlayers(4);
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
        assertPlayer(current, "4");
    }

    @Test
    public void WhenReversed_ShouldHaveDescendingOrder() {
        playerIterator.reverseDirection();

        var current = playerIterator.getCurrentPlayer();
        assertPlayer(current, "1");

        current = playerIterator.next();
        assertPlayer(current, "4");

        current = playerIterator.next();
        assertPlayer(current, "3");

        current = playerIterator.next();
        assertPlayer(current, "2");
    }

    @Test
    public void GivenValidPlayerId_ShouldFind(){
        var playerToFind = players[2];

        var foundPlayer = playerIterator.getPlayerById(playerToFind.getId());

        assertEquals(playerToFind.getName(), foundPlayer.getName());
    }

    @Test
    public void GivenInvalidPlayerId_ShouldNotFind(){
        var foundPlayer = playerIterator.getPlayerById(UUID.randomUUID());

        assertNull(foundPlayer);
    }

    private void assertPlayer(Player player, String expectedName){
        assertEquals(expectedName, player.getName());
    }
}
