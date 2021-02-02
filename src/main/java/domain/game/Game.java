package domain.game;

import domain.card.*;
import domain.common.DomainEventPublisher;
import domain.common.Entity;
import domain.game.events.CardPlayed;
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

    public Card peekTopCard() {
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
            default -> throw new UnsupportedOperationException("Unsupported card type " + card.getType());
        }
    }

    public void playCard(UUID playerId, Card playedCard) {
        validatePlayedCard(playerId, playedCard);

        switch (playedCard.getType()) {
            case NUMBER -> {
                checkNumberCardRule(playedCard);
                discard(playedCard);

                players.next();
            }
            case SKIP -> {
                checkActionCardRule(playedCard);
                discard(playedCard);

                players.next();
                players.next();
            }
            case REVERSE -> {
                checkActionCardRule(playedCard);
                discard(playedCard);

                reverse();
            }
            case DRAW_TWO -> {
                checkActionCardRule(playedCard);
                discard(playedCard);

                players.next();
                drawTwoCards(players.getCurrentPlayer());
                players.next();
            }
            case WILD_COLOR -> {
                checkWildCardRule(playedCard);
                discard(playedCard);

                players.next();
            }
            case WILD_DRAW_FOUR -> {
                checkWildCardRule(playedCard);
                discard(playedCard);

                players.next();
                drawFourCards(players.getCurrentPlayer());
                players.next();
            }
            default -> rejectPlayedCard(playedCard);
        }

        DomainEventPublisher.publish(new CardPlayed(playerId, playedCard));
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

    private void checkNumberCardRule(Card playedCard) {
        var topCard = peekTopCard();

        if (isFirstDiscardAWildCard() || CardRules.isValidNumberCard(topCard, (NumberCard) playedCard)) {
            return;
        }

        rejectPlayedCard(playedCard);
    }

    private void checkActionCardRule(Card playedCard) {
        var topCard = peekTopCard();

        if (isFirstDiscardAWildCard() || CardRules.isValidActionCard(topCard, (ActionCard) playedCard)) {
            return;
        }

        rejectPlayedCard(playedCard);
    }

    private void checkWildCardRule(Card playedCard) {
        if (!CardRules.isValidWildCard((WildCard) playedCard)) {
            rejectPlayedCard(playedCard);
        }
    }

    private boolean isFirstDiscardAWildCard() {
        return discardPile.size() == 1 && peekTopCard().getType() == CardType.WILD_COLOR;
    }

    private void recreateDrawPile(Card card) {
        if (drawPile.getSize() == 0) {
            throw new IllegalStateException("Not enough cards to recreate draw pile");
        }

        drawPile = DealerService.shuffle(drawPile, card);
    }

    private void discard(Card card) {
        discardPile.add(card);
        players.getCurrentPlayer().removePlayedCard(card);
    }

    private void reverse() {
        players.reverseDirection();
        players.next();
    }

    private void drawTwoCards(Player player) {
        drawCards(player, 2);
    }

    private void drawFourCards(Player player) {
        drawCards(player, 4);
    }

    private void drawCards(Player player, int total) {
        int min = Math.min(total, drawPile.getSize());

        for (int i = 0; i < min; i++) {
            player.addToHandCards(drawPile.drawCard());
        }
    }

    private void rejectPlayedCard(Card playedCard) {
        throw new IllegalArgumentException(
            String.format("Played card %s is not valid for %s", playedCard, peekTopCard()));
    }
}
