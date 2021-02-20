package domain.card;

import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCard {
    @Test
    void GivenSameNumberCardValues_ShouldBeEqual() {
        var numberCard = new NumberCard(1, CardColor.RED);
        var anotherNumberCard = new NumberCard(1, CardColor.RED);

        assertNotSame(numberCard, anotherNumberCard);
        assertEquals(numberCard, anotherNumberCard);
    }

    @Test
    void GivenDifferentNumberCardValues_ShouldNotBeEqual() {
        var numberCard = new NumberCard(1, CardColor.GREEN);
        var anotherNumberCard = new NumberCard(1, CardColor.RED);

        assertNotEquals(numberCard, anotherNumberCard);
    }

    @Test
    void GivenSameSkipCard_ShouldBeEqual() {
        var skipCard = CardTestFactory.createSkipCard(CardColor.RED);
        var anotherSkipCard = CardTestFactory.createSkipCard(CardColor.RED);

        assertEquals(skipCard, anotherSkipCard);
    }

    @Test
    void GivenDifferentSkipCard_ShouldNotBeEqual() {
        var skipCard = CardTestFactory.createSkipCard(CardColor.RED);
        var anotherSkipCard = CardTestFactory.createSkipCard(CardColor.GREEN);

        assertNotEquals(skipCard, anotherSkipCard);
    }

    @Test
    void GivenDifferentActionCard_ShouldNotBeEqual() {
        var skipCard = CardTestFactory.createSkipCard(CardColor.RED);
        var reverseCard = CardTestFactory.createReverseCard(CardColor.RED);

        assertNotEquals(skipCard, reverseCard);
    }

    @Test
    void GivenSameWildColorCard_ShouldBeEqual() {
        var card = CardTestFactory.createWildColorCard();
        var anotherCard = CardTestFactory.createWildColorCard();

        assertEquals(card, anotherCard);
    }

    @Test
    void GivenSameWildDrawFourCard_ShouldBeEqual() {
        var card = CardTestFactory.createWildDrawFourCard();
        var anotherCard = CardTestFactory.createWildDrawFourCard();

        assertEquals(card, anotherCard);
    }
}
