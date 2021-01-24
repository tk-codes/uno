package domain.game;

import domain.card.Card;
import domain.card.ColorCard;
import domain.card.NumberCard;
import domain.card.SkipCard;
import domain.player.ImmutablePlayer;
import domain.player.Player;
import domain.player.PlayerRoundIterator;

import java.util.Stack;
import java.util.stream.Stream;

public class Game {
    private final PlayerRoundIterator players;

    private final DrawPile drawPile;
    private final Stack<ColorCard> discardPile = new Stack<>();

    public Game(DrawPile drawPile, PlayerRoundIterator players) {
        this.drawPile = drawPile;
        this.players = players;

        startDiscardPile();
    }

    private void startDiscardPile() {
        var card = drawPile.drawCard();

        switch (card.getType()) {
            case NUMBER -> discardPile.add((NumberCard) card);
        }
    }

    public Stream<ImmutablePlayer> getPlayers() {
        return players.stream().map(Player::toImmutable);
    }

    public ImmutablePlayer getCurrentPlayer() {
        return players.getCurrentPlayer().toImmutable();
    }

    public ColorCard peekLastPlayedCard() {
        return discardPile.peek();
    }
}
