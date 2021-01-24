package domain.card;

import java.util.Objects;

public class ReverseCard implements ColorCard {
    private final CardColor color;

    public ReverseCard(CardColor color) {
        CardUtil.validateColor(color);

        this.color = color;
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.REVERSE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReverseCard that = (ReverseCard) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
