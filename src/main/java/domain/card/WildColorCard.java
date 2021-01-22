package domain.card;

public class WildColorCard implements Card {

    public WildColorCard() {
    }

    @Override
    public CardType getType() {
        return CardType.WILD_COLOR;
    }
}
