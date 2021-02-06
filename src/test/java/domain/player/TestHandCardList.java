package domain.player;

import domain.card.CardColor;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHandCardList {

    @Test
    public void WhenEmpty_ShouldNotRemove() {
        var handCards = new HandCardList();

        var result = handCards.removeCard(CardTestFactory.createSkipCard());

        assertFalse(result);
    }

    @Test
    public void GivenTwoCardsWithSameValues_ShouldRemoveOnlyOnce() {
        var handCards = new HandCardList();
        var numberCard1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        var numberCard2 = CardTestFactory.createNumberCard(1, CardColor.RED);
        handCards.addCard(numberCard1);
        handCards.addCard(numberCard2);

        var result = handCards.removeCard(numberCard1);

        assertTrue(result);
        assertEquals(1, handCards.size());
    }

    @Test
    public void GivenTwoCardsWithSameReference_ShouldRemoveOnlyOnce() {
        var handCards = new HandCardList();
        var numberCard1 = CardTestFactory.createNumberCard(1, CardColor.RED);
        handCards.addCard(numberCard1);
        handCards.addCard(numberCard1);

        var result = handCards.removeCard(numberCard1);

        assertTrue(result);
        assertEquals(1, handCards.size());
    }

    @Test
    public void GivenWildColorWithColor_ShouldRemoveOnlyOnce() {
        var handCards = new HandCardList();
        handCards.addCard(CardTestFactory.createWildColorCard());
        handCards.addCard(CardTestFactory.createWildColorCard());

        var result = handCards.removeCard(CardTestFactory.createWildColorCard(CardColor.GREEN));

        assertTrue(result);
        assertEquals(1, handCards.size());
    }

    @Test
    public void GivenWildDrawFourWithColor_WhenTypeDoesNotExist_ShouldNotRemove() {
        var handCards = new HandCardList();
        handCards.addCard(CardTestFactory.createWildColorCard());
        handCards.addCard(CardTestFactory.createWildColorCard());

        var result = handCards.removeCard(CardTestFactory.createWildDrawFourCard(CardColor.GREEN));

        assertFalse(result);
        assertEquals(2, handCards.size());
    }


}
