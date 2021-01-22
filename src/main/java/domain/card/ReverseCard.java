package domain.card;

public class ReverseCard implements Card {
    private final CardColor color;

    public ReverseCard(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.REVERSE;
    }
}
