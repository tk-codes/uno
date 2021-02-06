package domain.common;

public interface DomainEventSubscriber {
    void handleEvent(DomainEvent event);
}
