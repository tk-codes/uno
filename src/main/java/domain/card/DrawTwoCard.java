package domain.card;

import java.util.Objects;

public class DrawTwoCard implements Card {
    private final CardColor color;

    public DrawTwoCard(CardColor color) {
        CardUtil.validateColor(color);

        this.color = color;
    }

    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.DRAW_TWO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrawTwoCard that = (DrawTwoCard) o;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
