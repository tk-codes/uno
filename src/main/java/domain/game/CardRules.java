package domain.game;

import domain.card.Card;
import domain.card.CardType;
import domain.card.NumberCard;

public class CardRules {
    public static boolean isValidCard(Card topCard, NumberCard playedCard) {
        if (topCard.getColor() == playedCard.getColor()) {
            return true;
        }

        if (topCard.getType() == CardType.NUMBER) {
            return ((NumberCard) topCard).getValue() == playedCard.getValue();
        }

        return false;
    }
}
