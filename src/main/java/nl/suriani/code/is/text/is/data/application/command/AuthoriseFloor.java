package nl.suriani.code.is.text.is.data.application.command;

import java.util.Objects;

public record AuthoriseFloor(Integer floor) implements Command {
    public AuthoriseFloor {
        Objects.requireNonNull(floor);
    }
}
