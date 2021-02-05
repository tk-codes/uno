package application;

import application.dto.PlayerInfoDTO;
import domain.card.Card;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface IGameAppService extends Serializable {
    List<PlayerInfoDTO> getPlayerInfos();

    PlayerInfoDTO getCurrentPlayer();

    Stream<Card> getHandCards(UUID playerId);

    void playCard(UUID playerId, Card card);

    void drawCard(UUID playerId);

    void sayUNO(UUID playerId);

    Card peekTopCard();
}
