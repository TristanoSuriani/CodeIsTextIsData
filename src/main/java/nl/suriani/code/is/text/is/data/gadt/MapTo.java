package nl.suriani.code.is.text.is.data.gadt;

import java.util.function.Function;

public record MapTo<T, R>(Expression<T> expression, Function<T, R> mapper) implements Expression<R> {
    @Override
    public R eval() {
        return mapper.apply(expression.eval());
    }

    @Override
    public String explain() {
        return "(mapTo " + expression.explain() + " mapper)";
    }
}
