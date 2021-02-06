package domain.card;

import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCardUtil {
    @Test
    void GivenWildColorCard_ShouldBeWildCard() {
        var result = CardUtil.isWildCard(CardTestFactory.createWildColorCard());

        assertTrue(result);
    }

    @Test
    void GivenWildDrawFourCard_ShouldBeWildCard() {
        var result = CardUtil.isWildCard(CardTestFactory.createWildDrawFourCard());

        assertTrue(result);
    }

    @Test
    void GivenNumberCard_ShouldNotBeWildCard() {
        var result = CardUtil.isWildCard(CardTestFactory.createNumberCard());

        assertFalse(result);
    }
}
