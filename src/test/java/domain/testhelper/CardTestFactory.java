package domain.testhelper;

import domain.card.*;

public class CardTestFactory {
    public static NumberCard createNumberCard() {
        return new NumberCard(1, CardColor.RED);
    }

    public static NumberCard createNumberCard(int value, CardColor color) {
        return new NumberCard(value, color);
    }

    public static SkipCard createSkipCard() {
        return createSkipCard(CardColor.BLUE);
    }

    public static SkipCard createSkipCard(CardColor color) {
        return new SkipCard(color);
    }

    public static ReverseCard createReverseCard() {
        return createReverseCard(CardColor.BLUE);
    }

    public static ReverseCard createReverseCard(CardColor color) {
        return new ReverseCard(color);
    }

    public static DrawTwoCard createDrawTwoCard() {
        return createDrawTwoCard(CardColor.YELLOW);
    }

    public static DrawTwoCard createDrawTwoCard(CardColor color) {
        return new DrawTwoCard(color);
    }

    public static WildColorCard createWildColorCard() {
        return new WildColorCard();
    }

    public static WildDrawFourCard createWildDrawFourCard() {
        return new WildDrawFourCard();
    }
}
