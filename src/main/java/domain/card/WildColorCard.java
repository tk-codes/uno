package domain.card;

import java.util.Objects;

public class WildColorCard implements Card {
    private final CardType type = CardType.WILD_COLOR;

    public WildColorCard() {
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WildColorCard that = (WildColorCard) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
