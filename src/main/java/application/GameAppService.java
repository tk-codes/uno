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

public class GameAppService implements IGameAppService{
    private static final Logger logger = LogManager.getLogger("GameAppService");

    private final Game game;

    public GameAppService() {
        game = new GameBuilder()
            .withPlayer("Player 1")
            .withPlayer("Player 2")
            .build();

        // TODO: Log players and hand cards
    }

    @Override
    public List<ImmutablePlayer> getPlayers() {
        return game.getPlayers().collect(Collectors.toList());
    }

    @Override
    public void playCard(UUID playerId, Card card) {

    }

    @Override
    public void drawCard(UUID playerId) {

    }

    @Override
    public void sayUNO(UUID playerId) {

    }

    @Override
    public Card peekTopCard() {
        return game.peekTopCard();
    }
}
