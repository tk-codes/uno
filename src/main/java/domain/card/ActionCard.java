package domain.card;

import java.util.Objects;

public class ActionCard implements Card {
    private final CardType type;
    private final CardColor color;

    public ActionCard(CardType type, CardColor color) {
        CardUtil.validateActionType(type);
        CardUtil.validateColor(color);

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
        ActionCard that = (ActionCard) o;
        return type == that.type && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

    @Override
    public String toString() {
        return "ActionCard{" +
            type + ", " + color +
            '}';
    }
}
