package nl.suriani.code.is.text.is.data.application.usecase;

import java.util.Objects;

public record Symbol<T>(String name, Class<T> type) {

    public Symbol {
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
    }
}