package domain.game;

import domain.card.CardDeck;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDealerService {
    @Test
    public void WhenShuffled_ShouldHaveDifferentOrder() {
        // Arrange
        var originalList = new CardDeck().getImmutableCards();

        // Act
        var shuffledList = DealerService.shuffle(originalList);

        // Assert
        int totalEquals = 0;
        for (int i = 0; i < originalList.size(); i++) {
            if (originalList.get(i).equals(shuffledList.get(i))) {
                totalEquals++;
            }
        }
        assertNotEquals(originalList.size(), totalEquals);
        assertTrue(totalEquals < (originalList.size() * 0.2), "Shuffled cards should have less than 20% equal order (heuristically)");
    }
}
