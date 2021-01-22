package domain.card;

public class SkipCard implements Card {
    private final CardColor color;

    public SkipCard(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.SKIP;
    }
}
