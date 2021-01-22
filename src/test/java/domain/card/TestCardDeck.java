package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCardDeck {

    private CardDeck testee;

    @BeforeEach
    private void setup() {
        testee = new CardDeck();
    }

    @Test
    public void WhenInitialized_ShouldBeImmutable() {
        var cards = testee.getCards();

        Assertions.assertThrows(UnsupportedOperationException.class, () -> cards.remove(0));
    }

    @Test
    public void WhenInitialized_ShouldHave108Cards() {
        assertEquals(108, testee.getCards().size());
    }

    @Test
    public void WhenInitialized_ShouldHave76NumberCards() {
        var cards = testee.getCards();

        var numberCounts = new int[10];
        var expectedNumberCounts = new int[]{4, 8, 8, 8, 8, 8, 8, 8, 8, 8};

        var colorCounts = new int[4];
        var expectedColorCounts = new int[]{19,19,19,19};

        for (var card : cards) {
            if (card.getType() == CardType.NUMBER) {
                var numberCard = (NumberCard) card;

                numberCounts[numberCard.getValue()] += 1;
                colorCounts[numberCard.getColor().ordinal()] += 1;
            }
        }

        assertEquals(76, Arrays.stream(numberCounts).sum());
        assertArrayEquals(numberCounts, expectedNumberCounts);
        assertArrayEquals(colorCounts, expectedColorCounts);
    }
}