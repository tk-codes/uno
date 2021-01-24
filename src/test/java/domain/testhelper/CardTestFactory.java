package domain.testhelper;

import domain.card.*;

public class CardTestFactory {
    public static NumberCard createNumberCard() {
        return new NumberCard(1, CardColor.RED);
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
}
