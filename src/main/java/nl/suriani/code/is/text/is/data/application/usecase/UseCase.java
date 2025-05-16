package nl.suriani.code.is.text.is.data.application.usecase;

import nl.suriani.code.is.text.is.data.gadt.Expression;

public interface UseCase<C, R extends Result> {
    Expression<R> define();
}
