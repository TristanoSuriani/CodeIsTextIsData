package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;

public record Nothing() implements Expression<Nothing> {

    @Override
    public Nothing eval(Environment environment) {
        return this;
    }
}
