package nl.suriani.code.is.text.is.data.model;

import java.util.Objects;
import java.util.UUID;

public record ModeId(UUID value) {
    public ModeId {
        Objects.requireNonNull(value);
    }
}
