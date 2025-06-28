package nl.suriani.code.is.text.is.data.specification.invariant;

import java.util.function.Predicate;

public interface Invariants {
    static<C> Invariant<C> sometimes(String description, Predicate<C> predicate) {
        return new Invariant<C>(
                description,
                predicate,
                TypeInvariant.SOMETIMES
        );
    }

    static<C> Invariant<C> always(String description, Predicate<C> predicate) {
        return new Invariant<C>(
                description,
                predicate,
                TypeInvariant.ALWAYS
        );
    }

    static<C> Invariant<C> never(String description, Predicate<C> predicate) {
        return new Invariant<C>(
                description,
                predicate,
                TypeInvariant.NEVER
        );
    }
}
