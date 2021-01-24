package domain.player;

import domain.card.Card;

import java.util.UUID;
import java.util.stream.Stream;

public class Player {
    private final UUID id;
    private final String name;
    private final HandCardList handCards;

    public Player(String name, HandCardList handCards){
        id = UUID.randomUUID();
        this.name = name;
        this.handCards = handCards;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stream<Card> getHandCards() {
        return this.handCards.getCardStream();
    }

    public void addToHandCards(Card card){
        handCards.addCard(card);
    }

    public ImmutablePlayer toImmutable() {
        return new ImmutablePlayer(this);
    }
}
