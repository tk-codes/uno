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

    public int size() {
        return handCards.size();
    }
}
