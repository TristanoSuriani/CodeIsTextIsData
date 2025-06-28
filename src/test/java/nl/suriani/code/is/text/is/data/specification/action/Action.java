package nl.suriani.code.is.text.is.data.specification.action;

import java.util.function.UnaryOperator;

public interface Action<C> {
    C execute(C context);

    static<C> Action<C> define(UnaryOperator<C> init) {
        return init::apply;
    }
}
