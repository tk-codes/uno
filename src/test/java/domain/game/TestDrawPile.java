package domain.game;

import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDrawPile {
    @Test
    void WhenDrawn_ShouldReturnLastCard() {
        var numberCard = CardTestFactory.createNumberCard();
        var skipCard = CardTestFactory.createSkipCard();

        var drawPile = new DrawPile(Arrays.asList(numberCard, skipCard));
        var drawnCard = drawPile.drawCard();

        assertEquals(drawnCard, skipCard);
    }
}
