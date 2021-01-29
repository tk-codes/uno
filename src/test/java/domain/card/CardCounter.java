package domain.card;

import java.util.List;
import java.util.stream.Stream;

public class CardCounter {
    private final List<Card> cards;

    public CardCounter(List<Card> cards) {
        this.cards = cards;
    }

    public int[] getNumberCounts() {
        var numberCounts = new int[10];

        getNumberStream()
            .forEach(c -> numberCounts[c.getValue()] += 1);

        return numberCounts;
    }

    public int[] getNumberColorCounts() {
        var colorCounts = new int[4];

        getNumberStream()
            .forEach(c -> colorCounts[c.getColor().ordinal()] += 1);

        return colorCounts;
    }

    private Stream<NumberCard> getNumberStream() {
        return cards.stream()
            .filter(c -> c.getType() == CardType.NUMBER)
            .map(c -> (NumberCard) c);
    }

    public int[] getSkipColorCounts() {
        var colorCounts = new int[4];

        cards.stream()
            .filter(c -> c.getType() == CardType.SKIP)
            .map(Card::getColor)
            .forEach(color -> colorCounts[color.ordinal()] += 1);

        return colorCounts;
    }

    public int[] getReverseColorCounts() {
        var colorCounts = new int[4];

        cards.stream()
            .filter(c -> c.getType() == CardType.REVERSE)
            .map(Card::getColor)
            .forEach(color -> colorCounts[color.ordinal()] += 1);

        return colorCounts;
    }

    public int[] getDrawTwoColorCounts() {
        var colorCounts = new int[4];

        cards.stream()
            .filter(c -> c.getType() == CardType.DRAW_TWO)
            .map(Card::getColor)
            .forEach(color -> colorCounts[color.ordinal()] += 1);

        return colorCounts;
    }

    public long getWildCardCounts(CardType type) {
        return cards.stream()
            .filter(c -> c.getType() == type)
            .count();
    }
}
