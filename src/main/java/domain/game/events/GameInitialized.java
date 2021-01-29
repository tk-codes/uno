package domain.game.events;

import domain.common.DomainEvent;
import domain.game.DrawPile;
import domain.player.PlayerRoundIterator;

public class GameInitialized extends DomainEvent {
    private final DrawPile drawPile;
    private final PlayerRoundIterator players;

    public GameInitialized(DrawPile drawPile, PlayerRoundIterator players) {
        this.drawPile = drawPile;
        this.players = players;
    }
}
