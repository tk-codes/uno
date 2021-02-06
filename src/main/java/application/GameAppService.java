package application;

import application.dto.PlayerInfoDTO;
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
        game.getPlayers().forEach(p -> {
            var joinedCardValues = p.getHandCards()
                .map(Object::toString)
                .collect(Collectors.joining(","));

            logger.debug(String.format("Player %s with %s cards => [%s]", p.getName(), p.getTotalCards(), joinedCardValues));
        });
    }

    @Override
    public List<PlayerInfoDTO> getPlayerInfos() {
        return game.getPlayers()
            .map(p -> new PlayerInfoDTO(p.getId(), p.getName()))
            .collect(Collectors.toList());
    }

    @Override
    public PlayerInfoDTO getCurrentPlayer() {
        var currentPlayer = game.getCurrentPlayer();
        return new PlayerInfoDTO(currentPlayer.getId(), currentPlayer.getName());
    }

    @Override
    public Stream<Card> getHandCards(UUID playerId) {
        return game.getHandCards(playerId);
    }

    @Override
    public void playCard(UUID playerId, Card card, boolean hasSaidUno) {
        var message = String.format("Player %s plays %s %s", playerId, card, hasSaidUno ? "and said UNO!!!" : "");
        logger.info(message);
        game.playCard(playerId, card, hasSaidUno);
    }

    @Override
    public void drawCard(UUID playerId) {
        var message = String.format("Player %s draws a card", playerId);
        logger.info(message);
        game.drawCard(playerId);
    }

    @Override
    public Card peekTopCard() {
        return game.peekTopCard();
    }

    @Override
    public boolean isGameOver() {
        return game.isOver();
    }

    @Override
    public ImmutablePlayer getWinner() {
        return game.getWinner();
    }
}
