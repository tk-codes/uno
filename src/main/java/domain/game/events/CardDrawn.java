package domain.game.events;

import domain.common.DomainEvent;

import java.util.UUID;

public class CardDrawn extends DomainEvent {
    private final UUID playerId;

    public CardDrawn(UUID playerId){
        this.playerId = playerId;
    }

    public UUID getPlayerId() {
        return playerId;
    }
}
