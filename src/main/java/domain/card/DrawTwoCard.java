package domain.card;

public class DrawTwoCard implements Card {
    private final CardColor color;

    public DrawTwoCard(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.DRAW_TWO;
    }
}
