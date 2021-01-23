package domain.card;

import java.util.Objects;

public class NumberCard implements Card {
    private final int value;
    private final CardColor color;

    public NumberCard(int value, CardColor color) {
        CardUtil.validateNumber(value);
        CardUtil.validateColor(color);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberCard that = (NumberCard) o;
        return value == that.value && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, color);
    }
}
