package domain.game;

import domain.card.Card;

import java.util.List;
import java.util.Stack;

public class DrawPile {
    private final Stack<Card> drawPile = new Stack<>();

    public DrawPile(List<Card> shuffledCards) {
        drawPile.addAll(shuffledCards);
    }

    public Card drawCard() {
        return drawPile.pop();
    }

    public int getSize() {
        return drawPile.size();
    }
}
