package domain.card;

import java.util.Objects;

public class WildCard implements Card {
    private final CardType type;
    private final CardColor color;

    public WildCard(CardType type) {
        this(type, null);
    }

    public WildCard(CardType type, CardColor color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public CardColor getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildCard wildCard = (WildCard) o;
        return type == wildCard.type && color == wildCard.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

    @Override
    public String toString() {
        return "WildCard{" +
            type + ", " + color +
            '}';
    }
}
