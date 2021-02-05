package application.dto;

import java.util.UUID;

public class PlayerInfoDTO {
    private final UUID id;
    private final String name;

    public PlayerInfoDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
