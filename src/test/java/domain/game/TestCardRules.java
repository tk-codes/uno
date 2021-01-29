package domain.game;

import domain.card.Card;
import domain.card.CardColor;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardRules {
    // NumberCard
    // --> NumberCard (exact match, same color, same value)
    // --> ActionCard (same color)
    // --> WildCard (same color)

    @ParameterizedTest
    @MethodSource("provideValidTopCardsForNumberCard")
    public void WhenNumberCardPlayed_ShouldBeValid(Card topCard){
        var cardToPlay = CardTestFactory.createNumberCard(5, CardColor.RED);

        var result = CardRules.isValidCard(topCard, cardToPlay);

        assertTrue(result, createTestMessage(topCard, cardToPlay));
    }

    private static Stream<Arguments> provideValidTopCardsForNumberCard() {
        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.RED)),
            Arguments.of(CardTestFactory.createNumberCard(4, CardColor.RED)),
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.BLUE))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTopCardsForNumberCard")
    public void WhenNumberCardPlayed_ShouldBeInvalid(Card topCard){
        var cardToPlay = CardTestFactory.createNumberCard(5, CardColor.RED);

        var result = CardRules.isValidCard(topCard, cardToPlay);

        assertFalse(result, createTestMessage(topCard, cardToPlay));
    }

    private static Stream<Arguments> provideInvalidTopCardsForNumberCard() {
        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(4, CardColor.BLUE))
        );
    }

    private String createTestMessage(Card topCard, Card playedCard) {
        return String.format("Top Card %s, Played Card %s", topCard, playedCard);
    }
}
