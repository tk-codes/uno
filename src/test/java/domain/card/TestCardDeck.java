package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCardDeck {

    private CardDeck testee;

    @BeforeEach
    private void setup() {
        testee = new CardDeck();
    }

    @Test
    void WhenInitialized_ShouldBeImmutable() {
        var cards = testee.getImmutableCards();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> cards.remove(0));
    }

    @Test
    void WhenInitialized_ShouldHave108Cards() {
        assertEquals(108, testee.getImmutableCards().size());
    }

    @Test
    void WhenInitialized_ShouldHave76NumberCards() {
        var cards = testee.getImmutableCards();

        CardCounterAssertionHelper.assertNumberCards(cards);
    }

    @Test
    void WhenInitialized_ShouldHave8SkipCards() {
        var cards = testee.getImmutableCards();

        CardCounterAssertionHelper.assertSkipCards(cards);
    }

    @Test
    void WhenInitialized_ShouldHave8ReverseCards() {
        var cards = testee.getImmutableCards();

        CardCounterAssertionHelper.assertReverseCards(cards);
    }

    @Test
    void WhenInitialized_ShouldHave8DrawTwoCards() {
        var cards = testee.getImmutableCards();

        CardCounterAssertionHelper.assertDrawTwoCards(cards);
    }

    @Test
    void WhenInitialized_ShouldHave8WildCards() {
        var cards = testee.getImmutableCards();

        CardCounterAssertionHelper.assertWildCards(cards);
    }
}