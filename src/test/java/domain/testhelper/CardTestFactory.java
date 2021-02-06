package domain.testhelper;

import domain.card.*;

public class CardTestFactory {
    public static NumberCard createNumberCard() {
        return new NumberCard(1, CardColor.RED);
    }

    public static NumberCard createNumberCard(int value, CardColor color) {
        return new NumberCard(value, color);
    }

    public static ActionCard createActionCard(CardType type, CardColor color){
        return new ActionCard(type, color);
    }

    public static ActionCard createSkipCard() {
        return createSkipCard(CardColor.BLUE);
    }

    public static ActionCard createSkipCard(CardColor color) {
        return new ActionCard(CardType.SKIP, color);
    }

    public static ActionCard createReverseCard() {
        return createReverseCard(CardColor.BLUE);
    }

    public static ActionCard createReverseCard(CardColor color) {
        return new ActionCard(CardType.REVERSE, color);
    }

    public static ActionCard createDrawTwoCard() {
        return createDrawTwoCard(CardColor.YELLOW);
    }

    public static ActionCard createDrawTwoCard(CardColor color) {
        return new ActionCard(CardType.DRAW_TWO, color);
    }

    public static WildCard createWildCard(CardType type) {
        return new WildCard(type);
    }

    public static WildCard createWildColorCard() {
        return new WildCard(CardType.WILD_COLOR);
    }

    public static WildCard createWildColorCard(CardColor color) {
        return new WildCard(CardType.WILD_COLOR, color);
    }

    public static WildCard createWildDrawFourCard() {
        return new WildCard(CardType.WILD_DRAW_FOUR);
    }

    public static WildCard createWildDrawFourCard(CardColor color) {
        return new WildCard(CardType.WILD_DRAW_FOUR, color);
    }
}
