package nl.suriani.code.is.text.is.data.adt.deferred;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.adt.Expression;

public interface DeferredExpression<T> {
    Expression<T> materialise(Environment environment);

    default T eval(Environment env) {
        return materialise(env).eval(env);
    }
}
