package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.application.usecase.Symbol;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Environment {
    private final Map<Symbol, Object> symbolTable = new ConcurrentHashMap<>();

    public <T> void put(Symbol name, T value) {
        symbolTable.put(name, value);
    }

    public <T> T get(Symbol<T> key) {
        return Optional.ofNullable(symbolTable.get(key))
                .map(key.type()::cast)
                .orElseThrow(() -> new IllegalArgumentException("Symbol " + key.name() + " not found"));
    }
}
