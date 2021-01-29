package domain.game;

import domain.card.Card;
import domain.card.CardType;
import domain.card.NumberCard;
import domain.common.Entity;
import domain.player.ImmutablePlayer;
import domain.player.Player;
import domain.player.PlayerRoundIterator;

import java.util.Stack;
import java.util.UUID;
import java.util.stream.Stream;

public class Game extends Entity {
    private final PlayerRoundIterator players;

    private DrawPile drawPile;
    private final Stack<Card> discardPile = new Stack<>();

    public Game(DrawPile drawPile, PlayerRoundIterator players) {
        super();
        this.drawPile = drawPile;
        this.players = players;

        startDiscardPile();
    }

    public Stream<ImmutablePlayer> getPlayers() {
        return players.stream().map(Player::toImmutable);
    }

    public ImmutablePlayer getCurrentPlayer() {
        return players.getCurrentPlayer().toImmutable();
    }

    public Card peekLastPlayedCard() {
        return discardPile.peek();
    }

    private void startDiscardPile() {
        var card = drawPile.drawCard();

        switch (card.getType()) {
            case NUMBER, WILD_COLOR -> discard(card);
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
            case WILD_DRAW_FOUR -> {
                recreateDrawPile(card);
                startDiscardPile();
            }
        }
    }

    public void playCard(UUID playerId, Card card) {
        validatePlayedCard(playerId, card);

        var lastPlayedCard = peekLastPlayedCard();

        switch (card.getType()) {
            case NUMBER -> {
                if (isValidCard((NumberCard) card, lastPlayedCard)) {
                    discard(card);
                    players.next();
                }
            }
            default -> throw new IllegalArgumentException(String.format("Played card %s is not valid for %s", card, lastPlayedCard));
        }
    }

    private void validatePlayedCard(UUID playerId, Card card) {
        var currentPlayer = players.getCurrentPlayer();
        if (!currentPlayer.getId().equals(playerId)) {
            throw new IllegalArgumentException(String.format("Not the turn of Player ID %s", playerId));
        }

        if (!currentPlayer.hasHandCard(card)) {
            throw new IllegalArgumentException(String.format("Card %s does not exist in player's hand cards", card));
        }
    }

    private void recreateDrawPile(Card card) {
        if (drawPile.getSize() == 0) {
            throw new IllegalStateException("Not enough cards to recreate draw pile");
        }

        drawPile = DealerService.shuffle(drawPile, card);
    }

    private void discard(Card card) {
        discardPile.add(card);
    }

    private void reverse() {
        players.reverseDirection();
        players.next();
    }

    private void drawTwoCards(Player player) {
        drawCards(player, 2);
    }

    private void drawCards(Player player, int total) {
        int min = Math.min(total, drawPile.getSize());

        for (int i = 0; i < min; i++) {
            player.addToHandCards(drawPile.drawCard());
        }
    }

    private boolean isValidCard(NumberCard playedCard, Card topCard) {
        if (topCard.getColor() == playedCard.getColor()) {
            return true;
        } else if (topCard.getType() == CardType.NUMBER) {
            return ((NumberCard) topCard).getValue() == playedCard.getValue();
        }

        return false;
    }
}
