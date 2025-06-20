package nl.suriani.code.is.text.is.data.application.command;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CommandHandler<I, O> {
    private final Predicate<I> ignoreWhen;
    private final Function<I, O> mapTo;
    private final Consumer<O> act;

    public CommandHandler(Function<I, O> mapTo, Predicate<I> ignoreWhen, Consumer<O> act) {
        this.mapTo = mapTo;
        this.ignoreWhen = ignoreWhen;
        this.act = act;
    }

    public CommandHandler() {
        this.ignoreWhen = i -> false;
        this.mapTo = null;
        this.act = o -> {};
    }

    public void handle(I input) {
        if (ignoreWhen.test(input)) {
            return;
        }
        O output = mapTo.apply(input);
        act.accept(output);
    }
}
