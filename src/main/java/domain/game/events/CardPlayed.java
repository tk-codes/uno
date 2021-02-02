package domain.game.events;

import domain.card.Card;
import domain.common.DomainEvent;

import java.util.UUID;

public class CardPlayed extends DomainEvent {
    private final UUID playerId;
    private final Card playedCard;

    public CardPlayed(UUID playerId, Card playedCard) {
        this.playerId = playerId;
        this.playedCard = playedCard;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public Card getPlayedCard() {
        return playedCard;
    }
}
