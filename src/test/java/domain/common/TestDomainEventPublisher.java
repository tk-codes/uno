package domain.common;

import domain.card.Card;
import domain.card.CardColor;
import domain.game.events.CardPlayed;
import domain.testhelper.CardTestFactory;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestDomainEventPublisher {
    class TestSubscriber implements DomainEventSubscriber {
        int timesInvoked = 0;
        Card playedCard = null;

        @Override
        public void handleEvent(DomainEvent event) {
            timesInvoked += 1;
            playedCard = ((CardPlayed) event).getPlayedCard();
        }
    }

    @Test
    void WhenEventPublished_ShouldInvokeSubscriber() {
        // Arrange
        var subscriber = new TestSubscriber();

        // act
        DomainEventPublisher.subscribe(subscriber);
        DomainEventPublisher.publish(new CardPlayed(UUID.randomUUID(),
            CardTestFactory.createNumberCard(1, CardColor.RED)));
        DomainEventPublisher.unsubscribe(subscriber);

        // assert
        assertEquals(1, subscriber.timesInvoked);
        assertEquals(CardTestFactory.createNumberCard(1, CardColor.RED), subscriber.playedCard);
    }

    @Test
    void WhenUnsubscribed_ShouldNotInvokeSubscriber() {
        // Arrange
        var subscriber = new TestSubscriber();

        // act
        DomainEventPublisher.subscribe(subscriber);
        DomainEventPublisher.unsubscribe(subscriber);
        DomainEventPublisher.publish(new CardPlayed(UUID.randomUUID(),
            CardTestFactory.createNumberCard(1, CardColor.RED)));

        // assert
        assertEquals(0, subscriber.timesInvoked);
        assertNull(subscriber.playedCard);
    }
}
