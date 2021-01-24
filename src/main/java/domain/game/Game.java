package domain.game;

import domain.card.Card;
import domain.card.ColorCard;
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
            case NUMBER -> discard(card);
            case SKIP -> {
                discard(card);
                players.next();
            }
            case REVERSE -> {
                discard(card);
                reverse();
            }
            case DRAW_TWO -> {
                discard(card);
                drawTwoCards(players.getCurrentPlayer());
                players.next();
            }
        }
    }

    private void discard(Card card){
        discardPile.add((ColorCard) card);
    }

    private void reverse() {
        players.reverseDirection();
        players.next();
    }

    private void drawTwoCards(Player player){
        drawCards(player, 2);
    }

    private void drawCards(Player player, int total){
        int min = Math.min(total, drawPile.getSize());

        for (int i = 0; i < min; i++) {
            player.addToHandCards(drawPile.drawCard());
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
