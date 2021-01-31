package application;

import domain.card.Card;
import domain.player.ImmutablePlayer;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface IGameAppService extends Serializable {
    List<ImmutablePlayer> getPlayers();

    void playCard(UUID playerId, Card card);

    void drawCard(UUID playerId);

    void sayUNO(UUID playerId);

    Card peekTopCard();
}
