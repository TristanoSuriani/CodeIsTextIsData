package nl.suriani.code.is.text.is.data.model;

import java.util.Objects;
import java.util.UUID;

public record MicrochipId(UUID value) {
    public MicrochipId {
        Objects.requireNonNull(value);
    }
}
