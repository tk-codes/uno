package domain.game.events;

import domain.common.DomainEvent;
import domain.player.ImmutablePlayer;

public class GameOver extends DomainEvent {
    private ImmutablePlayer winner;

    public GameOver(ImmutablePlayer winner) {
        this.winner = winner;
    }

    public ImmutablePlayer getWinner() {
        return winner;
    }
}
