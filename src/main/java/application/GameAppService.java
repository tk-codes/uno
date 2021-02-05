package application;

import domain.card.Card;
import domain.game.Game;
import domain.game.GameBuilder;
import domain.player.ImmutablePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameAppService implements IGameAppService {
    private static final Logger logger = LogManager.getLogger("GameAppService");

    private final Game game;

    public GameAppService() {
        game = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2")
            .build();

        logGameInfo();
    }

    private void logGameInfo() {
        logger.info("Game created successfully");
        getPlayers().forEach(p -> {
            var joinedCardValues = p.getHandCards()
                .map(Object::toString)
                .collect(Collectors.joining(","));

            logger.debug(String.format("Player %s with %s cards => [%s]", p.getName(), p.getTotalCards(), joinedCardValues));
        });
    }

    @Override
    public List<ImmutablePlayer> getPlayers() {
        return game.getPlayers().collect(Collectors.toList());
    }

    @Override
    public Stream<Card> getHandCards(UUID playerId) {
        return game.getHandCards(playerId);
    }

    @Override
    public void playCard(UUID playerId, Card card) {
        logger.info(String.format("Player %s played %s", playerId, card));
        game.playCard(playerId, card);
    }

    @Override
    public void drawCard(UUID playerId) {
        logger.info(String.format("Player %s draws a card", playerId));
    }

    @Override
    public void sayUNO(UUID playerId) {
        logger.info(String.format("Player %s said UNO", playerId));
    }

    @Override
    public Card peekTopCard() {
        return game.peekTopCard();
    }
}
