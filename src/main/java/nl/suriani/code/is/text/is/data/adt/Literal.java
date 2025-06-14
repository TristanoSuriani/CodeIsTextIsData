package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;

import java.util.Objects;

public record Literal<T>(T value) implements Expression<T> {
    public Literal {
        Objects.requireNonNull(value);
    }

    @Override
    public T eval(Environment environment) {
        return value;
    }
}
