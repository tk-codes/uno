package domain.card;

public class WildDrawFourCard implements Card {

    public WildDrawFourCard() {
    }

    @Override
    public CardType getType() {
        return CardType.WILD_DRAW_FOUR;
    }
}
