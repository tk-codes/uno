package domain.game;

import domain.card.Card;
import domain.card.CardType;
import domain.player.Player;
import domain.player.PlayerRoundIterator;
import domain.testhelper.CardTestFactory;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestGameStart {
    private final PlayerRoundIterator players = new PlayerRoundIterator(PlayerTestFactory.createPlayers(3));

    @Test
    void WhenNumberCardPlayed_ShouldHaveNoEffect() {
        var game = createGame(CardTestFactory.createNumberCard());

        assertGameState(game, CardType.NUMBER, "1");
    }

    @Test
    void WhenSkipCardPlayed_CurrentPlayerShouldBeSkipped() {
        var game = createGame(CardTestFactory.createSkipCard());

        assertGameState(game, CardType.SKIP, "2");
    }

    @Test
    void WhenReverseCardPlayed_DirectionShouldBeReversed() {
        var game = createGame(CardTestFactory.createReverseCard());

        assertGameState(game, CardType.REVERSE, "3");
    }

    @Test
    void WhenDrawTwoCardPlayed_FirstPlayerShouldGetTwoCards() {
        var game = createGame(
            CardTestFactory.createNumberCard(),
            CardTestFactory.createSkipCard(),
            CardTestFactory.createDrawTwoCard());

        var previousPlayer = players.stream().toArray(Player[]::new)[0];

        assertGameState(game, CardType.DRAW_TWO, "2");
        assertEquals(2, previousPlayer.getHandCards().count());
    }

    @Test
    void WhenWildColorCardPlayed_ShouldHaveNoEffect() {
        var game = createGame(CardTestFactory.createWildColorCard());

        assertGameState(game, CardType.WILD_COLOR, "1");
    }

    @Test
    void GivenOnlyOneCard_WhenWildDrawFourCardPlayed_ShouldThrowError() {
        assertThrows(IllegalStateException.class, () -> createGame(CardTestFactory.createWildDrawFourCard()));
    }

    @Test
    void WhenWildDrawFourCardPlayed_ShouldShuffleUntilPlayableCardFound() {
        var game = createGame(CardTestFactory.createNumberCard(), CardTestFactory.createWildDrawFourCard());

        assertGameState(game, CardType.NUMBER, "1");
    }

    private Game createGame(Card... cards) {
        var drawPile = createDrawPile(cards);

        return new Game(drawPile, players);
    }

    private DrawPile createDrawPile(Card... cards) {
        return new DrawPile(Arrays.asList(cards));
    }

    private void assertGameState(Game game, CardType expectedPlayedCardType, String expectedCurrentPlayer) {
        assertEquals(expectedPlayedCardType, game.peekTopCard().getType());
        assertEquals(expectedCurrentPlayer, game.getCurrentPlayer().getName());
    }
}
