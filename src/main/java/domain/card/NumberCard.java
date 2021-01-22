package domain.card;

public class NumberCard implements Card {
    private final int value;
    private final CardColor color;

    public NumberCard(int value, CardColor color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.NUMBER;
    }
}
