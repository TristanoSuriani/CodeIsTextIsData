package nl.suriani.code.is.text.is.data.model;

import java.util.Objects;
import java.util.UUID;


public record Microchip(MicrochipId id, String namePet, ModeId activeMode) {

    public Microchip {
        Objects.requireNonNull(id);
        Objects.requireNonNull(namePet);
        Objects.requireNonNull(activeMode);
    }
}
