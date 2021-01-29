package domain.game;

import domain.card.Card;
import domain.card.CardColor;
import domain.player.Player;
import domain.player.PlayerRoundIterator;
import domain.testhelper.CardTestFactory;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGamePlay {
    private final Player[] players = PlayerTestFactory.createPlayers(3);
    private final PlayerRoundIterator playersIterator = new PlayerRoundIterator(players);

    @Test
    void WhenInvalidPlayerPlayed_ShouldBeRejected() {
        // Arrange
        var cardToPlay = CardTestFactory.createNumberCard();
        var game = createGame(cardToPlay, CardTestFactory.createNumberCard());

        var playerToPlay = players[2].getId();

        // Act
        assertThrows(IllegalArgumentException.class, () -> game.playCard(playerToPlay, cardToPlay));
    }

    @Test
    void WhenNonExistingCardPlayed_ShouldBeRejected() {
        // Arrange
        var handCard = CardTestFactory.createNumberCard(1, CardColor.RED);
        var cardToPlay = CardTestFactory.createNumberCard(2, CardColor.RED);

        var game = createGame(handCard, CardTestFactory.createNumberCard());

        // Act
        assertThrows(IllegalArgumentException.class, () -> playCardFromCurrentPlayer(game, cardToPlay));
    }

    @ParameterizedTest
    @MethodSource("provideValidNumberCards")
    public void WhenValidNumberCardPlayed_ShouldBeAccepted(Card topCard, Card cardToPlay) {
        // Arrange
        var game = createGame(cardToPlay, topCard);

        // Act
        playCardFromCurrentPlayer(game, cardToPlay);

        // Assert
        assertGameState(game, cardToPlay, "2");
    }

    private static Stream<Arguments> provideValidNumberCards() {
        var cardToPlay = CardTestFactory.createNumberCard(4, CardColor.BLUE);

        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.BLUE), cardToPlay),
            Arguments.of(CardTestFactory.createWildColorCard(), cardToPlay)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidSkipCards")
    public void WhenValidSkipCardPlayed_ShouldBeAccepted(Card topCard, Card cardToPlay) {
        // Arrange
        var game = createGame(cardToPlay, topCard);

        // Act
        playCardFromCurrentPlayer(game, cardToPlay);

        // Assert
        assertGameState(game, cardToPlay, "3");
    }

    private static Stream<Arguments> provideValidSkipCards() {
        var cardToPlay = CardTestFactory.createSkipCard(CardColor.YELLOW);

        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.YELLOW), cardToPlay),
            Arguments.of(CardTestFactory.createWildColorCard(), cardToPlay)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNumberCards")
    public void WhenInvalidCardPlayed_ShouldBeRejected(Card topCard, Card cardToPlay) {
        // Arrange
        var game = createGame(cardToPlay, topCard);

        // Assert
        assertThrows(IllegalArgumentException.class, () -> playCardFromCurrentPlayer(game, cardToPlay));
        assertGameState(game, topCard, "1");
    }

    private static Stream<Arguments> provideInvalidNumberCards() {
        var topCard = CardTestFactory.createNumberCard(5, CardColor.BLUE);

        return Stream.of(
            Arguments.of(topCard, CardTestFactory.createNumberCard(4, CardColor.RED)),
            Arguments.of(topCard, CardTestFactory.createSkipCard(CardColor.RED))
        );
    }

    private Game createGame(Card cardToPlay, Card... drawPileCards) {
        var drawPile = createDrawPile(drawPileCards);

        var game = new Game(drawPile, playersIterator);
        playersIterator.getCurrentPlayer().addToHandCards(cardToPlay);

        return game;
    }

    private DrawPile createDrawPile(Card... cards) {
        return new DrawPile(Arrays.asList(cards));
    }

    private void playCardFromCurrentPlayer(Game game, Card cardToPlay) {
        game.playCard(playersIterator.getCurrentPlayer().getId(), cardToPlay);
    }

    private void assertGameState(Game game, Card expectedTopCard, String expectedCurrentPlayer) {
        assertEquals(expectedTopCard, game.peekTopCard());
        assertEquals(expectedCurrentPlayer, game.getCurrentPlayer().getName());
    }
}
