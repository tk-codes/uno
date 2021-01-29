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
        return new SkipCard(CardColor.BLUE);
    }

    public static ReverseCard createReverseCard() {
        return new ReverseCard(CardColor.BLUE);
    }

    public static DrawTwoCard createDrawTwoCard() {
        return new DrawTwoCard(CardColor.YELLOW);
    }

    public static WildColorCard createWildColorCard() {
        return new WildColorCard();
    }

    public static WildDrawFourCard createWildDrawFourCard() {
        return new WildDrawFourCard();
    }
}
