package domain.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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
        var expectedColorCounts = new int[]{19, 19, 19, 19};

        for (var card : cards) {
            if (card.getType() == CardType.NUMBER) {
                var numberCard = (NumberCard) card;

                numberCounts[numberCard.getValue()] += 1;
                colorCounts[numberCard.getColor().ordinal()] += 1;
            }
        }

        assertEquals(76, Arrays.stream(numberCounts).sum());
        assertArrayEquals(expectedNumberCounts, numberCounts);
        assertArrayEquals(expectedColorCounts, colorCounts);
    }

    @Test
    public void WhenInitialized_ShouldHave8SkipCards() {
        var cards = testee.getCards();

        var colorCounts = new int[4];

        for (var card : cards) {
            if (card.getType() == CardType.SKIP) {
                var skipCard = (SkipCard) card;

                colorCounts[skipCard.getColor().ordinal()] += 1;
            }
        }

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertTrue(Arrays.stream(colorCounts).allMatch((i) -> i == 2));
    }

    @Test
    public void WhenInitialized_ShouldHave8ReverseCards() {
        var cards = testee.getCards();

        var colorCounts = new int[4];
        var expectedColorCounts = new int[4];
        Arrays.fill(expectedColorCounts, 2);

        for (var card : cards) {
            if (card.getType() == CardType.REVERSE) {
                var reverseCard = (ReverseCard) card;

                colorCounts[reverseCard.getColor().ordinal()] += 1;
            }
        }

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertArrayEquals(expectedColorCounts, colorCounts);
    }

    @Test
    public void WhenInitialized_ShouldHave8DrawTwoCards() {
        var cards = testee.getCards();

        var colorCounts = new int[4];
        var expectedColorCounts = new int[4];
        Arrays.fill(expectedColorCounts, 2);

        for (var card : cards) {
            if (card.getType() == CardType.DRAW_TWO) {
                var drawTwoCard = (DrawTwoCard) card;

                colorCounts[drawTwoCard.getColor().ordinal()] += 1;
            }
        }

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertArrayEquals(expectedColorCounts, colorCounts);
    }

    @Test
    public void WhenInitialized_ShouldHave8WildCards() {
        var cards = testee.getCards();

        var wildColorCardCount = 0;
        var wildDrawCardCount = 0;

        for (var card : cards) {
            if (card.getType() == CardType.WILD_COLOR) {
                wildColorCardCount++;
            }else if(card.getType() == CardType.WILD_DRAW_FOUR) {
                wildDrawCardCount++;
            }
        }

        assertEquals(4, wildColorCardCount);
        assertEquals(4, wildDrawCardCount);
    }
}