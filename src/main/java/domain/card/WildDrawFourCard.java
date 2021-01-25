package domain.card;

import java.util.Objects;

public class WildDrawFourCard implements Card {
    private final CardType type = CardType.WILD_DRAW_FOUR;

    public WildDrawFourCard() {
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public CardColor getColor() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildDrawFourCard that = (WildDrawFourCard) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
