package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HandCardList {
    private final List<Card> handCards = new ArrayList<>();

    public void addCard(Card newCard) {
        handCards.add(newCard);
    }

    public Stream<Card> getCardStream() {
        return handCards.stream();
    }

    public boolean hasCard(Card card) {
        return getCardStream().anyMatch(c -> c.equals(card));
    }

    public int size() {
        return handCards.size();
    }
}
