package domain.game;

import domain.card.*;
import domain.player.HandCardList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestDealerService {
    @Test
    void WhenShuffled_ShouldHaveDifferentOrder() {
        // Arrange
        var originalList = new CardDeck().getImmutableCards();

        // Act
        var shuffledList = DealerService.shuffle(originalList);

        // Assert
        int totalEquals = 0;
        for (int i = 0; i < originalList.size(); i++) {
            if (originalList.get(i).equals(shuffledList.get(i))) {
                totalEquals++;
            }
        }
        assertNotEquals(originalList.size(), totalEquals);
        assertTrue(totalEquals < (originalList.size() * 0.2), "Shuffled cards should have less than 20% equal order (heuristically)");
    }

    @Test
    void WhenShuffled_ShouldHaveAllNumberCards() {
        var shuffledList = getShuffledCards();

        CardCounterAssertionHelper.assertNumberCards(shuffledList);
    }

    @Test
    void WhenShuffled_ShouldHaveAllSkipCards() {
        var shuffledList = getShuffledCards();

        CardCounterAssertionHelper.assertSkipCards(shuffledList);
    }

    @Test
    void WhenShuffled_ShouldHaveAllReverseCards() {
        var shuffledList = getShuffledCards();

        CardCounterAssertionHelper.assertReverseCards(shuffledList);
    }

    @Test
    void WhenShuffled_ShouldHaveAllDrawTwoCards() {
        var shuffledList = getShuffledCards();

        CardCounterAssertionHelper.assertDrawTwoCards(shuffledList);
    }

    @Test
    void WhenShuffled_ShouldHaveAllWildCards() {
        var shuffledList = getShuffledCards();

        CardCounterAssertionHelper.assertWildCards(shuffledList);
    }

    private List<Card> getShuffledCards() {
        var originalList = new CardDeck().getImmutableCards();
        return DealerService.shuffle(originalList);
    }

    @Test
    void WhenDealt_ShouldHave7CardsPerEachPlayer() {
        // Arrange
        var fixedCards = new ArrayList<Card>();
        for (int i = 0; i < 7; i++) {
            fixedCards.add(new NumberCard(1, CardColor.RED));
            fixedCards.add(new NumberCard(2, CardColor.GREEN));
            fixedCards.add(new NumberCard(3, CardColor.BLUE));
        }
        var drawPile = new DrawPile(fixedCards);

        // Act
        var handCardLists = DealerService.dealInitialHandCards(drawPile, 3);

        // Assert
        assertTrue(hasAllSameValues(handCardLists[0], 3, CardColor.BLUE));
        assertTrue(hasAllSameValues(handCardLists[1], 2, CardColor.GREEN));
        assertTrue(hasAllSameValues(handCardLists[2], 1, CardColor.RED));

        assertEquals(0, drawPile.getSize());
    }

    private boolean hasAllSameValues(HandCardList handCardList, int number, CardColor color) {
        if (handCardList.size() != 7) {
            return false;
        }

        return handCardList
            .getCardStream()
            .map(c -> (NumberCard) c)
            .allMatch(c -> c.getValue() == number && c.getColor() == color);
    }
}
