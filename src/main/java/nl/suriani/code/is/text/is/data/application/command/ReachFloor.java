package nl.suriani.code.is.text.is.data.application.command;

import java.util.Objects;

public record ReachFloor(Integer floor) implements Command {
    public ReachFloor {
        Objects.requireNonNull(floor);
    }
}