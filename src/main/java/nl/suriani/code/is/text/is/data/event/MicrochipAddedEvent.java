package nl.suriani.code.is.text.is.data.event;

import java.util.Objects;
import java.util.UUID;

public record MicrochipAddedEvent(UUID microchipId, String namePet) implements Event {

    public MicrochipAddedEvent {
        Objects.requireNonNull(microchipId);
        Objects.requireNonNull(namePet);
    }
}
