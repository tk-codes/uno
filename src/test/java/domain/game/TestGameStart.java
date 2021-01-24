package domain.game;

import domain.card.Card;
import domain.card.CardType;
import domain.card.NumberCard;
import domain.player.PlayerRoundIterator;
import domain.testhelper.CardTestFactory;
import domain.testhelper.PlayerTestFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameStart {
    @Test
    void WhenNumberCardPlayed_ShouldHaveNoEffect() {
        var drawPile = createDrawPile(CardTestFactory.createNumberCard());
        var players = new PlayerRoundIterator(PlayerTestFactory.createPlayers(3));

        var game = new Game(drawPile, players);

        assertEquals(CardType.NUMBER, game.peekLastPlayedCard().getType());
        assertEquals("1", game.getCurrentPlayer().getName());
    }

    private DrawPile createDrawPile(Card... cards){
        return new DrawPile(Arrays.asList(cards));
    }
}
