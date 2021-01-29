package domain.game;

import domain.card.ActionCard;
import domain.card.Card;
import domain.card.CardColor;
import domain.card.CardType;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCardRules {

    @ParameterizedTest
    @MethodSource("provideValidTopCardsForNumberCard")
    public void WhenNumberCardPlayed_ShouldBeValid(Card topCard) {
        var cardToPlay = CardTestFactory.createNumberCard(5, CardColor.RED);

        var result = CardRules.isValidNumberCard(topCard, cardToPlay);

        assertTrue(result, createTestMessage(topCard, cardToPlay));
    }

    private static Stream<Arguments> provideValidTopCardsForNumberCard() {
        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.RED)),
            Arguments.of(CardTestFactory.createNumberCard(4, CardColor.RED)),
            Arguments.of(CardTestFactory.createNumberCard(5, CardColor.BLUE)),
            Arguments.of(CardTestFactory.createSkipCard(CardColor.RED)),
            Arguments.of(CardTestFactory.createReverseCard(CardColor.RED)),
            Arguments.of(CardTestFactory.createDrawTwoCard(CardColor.RED)),
            Arguments.of(CardTestFactory.createWildColorCard(CardColor.RED)),
            Arguments.of(CardTestFactory.createWildDrawFourCard(CardColor.RED))
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTopCardsForNumberCard")
    public void WhenNumberCardPlayed_ShouldBeInvalid(Card topCard) {
        var cardToPlay = CardTestFactory.createNumberCard(5, CardColor.RED);

        var result = CardRules.isValidNumberCard(topCard, cardToPlay);

        assertFalse(result, createTestMessage(topCard, cardToPlay));
    }

    private static Stream<Arguments> provideInvalidTopCardsForNumberCard() {
        return Stream.of(
            Arguments.of(CardTestFactory.createNumberCard(4, CardColor.BLUE)),
            Arguments.of(CardTestFactory.createSkipCard(CardColor.BLUE)),
            Arguments.of(CardTestFactory.createReverseCard(CardColor.BLUE)),
            Arguments.of(CardTestFactory.createDrawTwoCard(CardColor.BLUE)),
            Arguments.of(CardTestFactory.createWildColorCard(CardColor.BLUE)),
            Arguments.of(CardTestFactory.createWildDrawFourCard(CardColor.BLUE)),
            Arguments.of(CardTestFactory.createWildColorCard()),
            Arguments.of(CardTestFactory.createWildDrawFourCard())
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidTopCardsForActionCard")
    public void WhenActionCardPlayed_ShouldBeValid(Card cardToPlay, Card topCard) {
        var result = CardRules.isValidActionCard(topCard, (ActionCard) cardToPlay);

        assertTrue(result, createTestMessage(topCard, cardToPlay));
    }

    private static Stream<Arguments> provideValidTopCardsForActionCard() {
        var arguments = new ArrayList<Arguments>();
        var actionTypes = new CardType[]{CardType.SKIP, CardType.REVERSE, CardType.DRAW_TWO};

        for (var action : actionTypes) {
            var cardToPlay = CardTestFactory.createActionCard(action, CardColor.YELLOW);

            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createActionCard(action, CardColor.RED)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createNumberCard(5, CardColor.YELLOW)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createSkipCard(CardColor.YELLOW)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createReverseCard(CardColor.YELLOW)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createDrawTwoCard(CardColor.YELLOW)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createWildColorCard(CardColor.YELLOW)));
            arguments.add(Arguments.of(cardToPlay, CardTestFactory.createWildDrawFourCard(CardColor.YELLOW)));
        }

        return arguments.stream();
    }

    private String createTestMessage(Card topCard, Card playedCard) {
        return String.format("Top Card %s, Played Card %s", topCard, playedCard);
    }
}
