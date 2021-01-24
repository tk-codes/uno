package domain.card;

import java.util.Objects;

public class SkipCard implements ColorCard {
    private final CardColor color;

    public SkipCard(CardColor color) {
        CardUtil.validateColor(color);

        this.color = color;
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public CardType getType() {
        return CardType.SKIP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkipCard skipCard = (SkipCard) o;
        return color == skipCard.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
