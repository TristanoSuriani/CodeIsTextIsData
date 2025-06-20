package nl.suriani.code.is.text.is.data.application.command;

import java.util.Objects;

public sealed interface Outcome {
    record Success() implements Outcome {}
    record Failure(String reason) implements Outcome {
        public Failure {
            Objects.requireNonNull(reason);
        }
    }
    record Ignore() implements Outcome {}
}
