package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;

import java.util.function.Function;

public record MapTo<T, R>(Expression<T> expression, Function<T, R> mapper) implements Expression<R> {
    @Override
    public R eval(Environment environment) {
        return mapper.apply(expression.eval(environment));
    }
}
