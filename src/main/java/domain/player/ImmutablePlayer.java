package domain.player;

import domain.card.Card;

import java.util.UUID;
import java.util.stream.Stream;

public class ImmutablePlayer {
    private final Player player;

    public ImmutablePlayer(Player player) {
        this.player = player;
    }

    public UUID getId() {
        return player.getId();
    }

    public String getName() {
        return player.getName();
    }

    public Stream<Card> getHandCards() {
        return this.player.getHandCards();
    }
}
