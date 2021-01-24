package domain.testhelper;

import domain.card.CardColor;
import domain.card.NumberCard;

public class CardTestFactory {
    public static NumberCard createNumberCard() {
        return new NumberCard(1, CardColor.RED);
    }
}
