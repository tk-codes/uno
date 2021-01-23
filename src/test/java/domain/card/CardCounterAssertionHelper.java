package domain.card;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardCounterAssertionHelper {

    public static void assertNumberCards(List<Card> cards) {
        var expectedNumberCounts = new int[]{4, 8, 8, 8, 8, 8, 8, 8, 8, 8};
        var expectedColorCounts = new int[]{19, 19, 19, 19};

        var counter = new CardCounter(cards);
        var numberCounts = counter.getNumberCounts();
        var colorCounts = counter.getNumberColorCounts();

        assertEquals(76, Arrays.stream(numberCounts).sum());
        assertArrayEquals(expectedNumberCounts, numberCounts);
        assertArrayEquals(expectedColorCounts, colorCounts);
    }

    public static void assertSkipCards(List<Card> cards) {
        var counter = new CardCounter(cards);
        var colorCounts = counter.getSkipColorCounts();

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertTrue(Arrays.stream(colorCounts).allMatch((i) -> i == 2));
    }

    public static void assertReverseCards(List<Card> cards) {
        var counter = new CardCounter(cards);
        var colorCounts = counter.getReverseColorCounts();

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertTrue(Arrays.stream(colorCounts).allMatch((i) -> i == 2));
    }

    public static void assertDrawTwoCards(List<Card> cards) {
        var counter = new CardCounter(cards);
        var colorCounts = counter.getDrawTwoColorCounts();

        assertEquals(8, Arrays.stream(colorCounts).sum());
        assertTrue(Arrays.stream(colorCounts).allMatch((i) -> i == 2));
    }

    public static void assertWildCards(List<Card> cards) {
        var counter = new CardCounter(cards);
        var wildColorCardCount = counter.getWildCardCounts(CardType.WILD_COLOR);
        var wildDrawCardCount = counter.getWildCardCounts(CardType.WILD_DRAW_FOUR);

        assertEquals(4, wildColorCardCount);
        assertEquals(4, wildDrawCardCount);
    }
}
