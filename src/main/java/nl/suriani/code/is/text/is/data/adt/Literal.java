package nl.suriani.code.is.text.is.data.adt;

import java.util.Objects;

public record Literal<T>(T value) implements Expression<T> {
    public Literal {
        Objects.requireNonNull(value);
    }

    @Override
    public T eval() {
        return value;
    }

    @Override
    public String explain() {
        return "(Literal " + value.getClass().getSimpleName() + ")";
    }
}
