package domain.common;

import java.util.UUID;

public abstract class Entity {
    private final UUID id;

    public Entity(){
        this(UUID.randomUUID());
    }

    public Entity(UUID id){
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
