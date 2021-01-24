package domain.player;

import java.util.UUID;

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
}
