package domain.common;

import java.util.Date;

public abstract class DomainEvent {
    private final Date occurredDate;

    protected DomainEvent(){
        occurredDate = new Date();
    }

    public Date getOccurredDate(){
        return occurredDate;
    }
}
