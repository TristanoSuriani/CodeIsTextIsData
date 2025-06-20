package nl.suriani.code.is.text.is.data.application.command;

import nl.suriani.code.is.text.is.data.event.Event;
import nl.suriani.code.is.text.is.data.event.EventListenerImpl;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventBasedCommandHandler<C extends Command, CTX, E extends Event> {
    private final Function<C,CTX> fetchContext;
    private final Predicate<CTX> isIdempotent;
    private final Consumer<CTX> validate;
    private final Function<CTX, E> decide;
    private final EventListenerImpl eventListener;

    public EventBasedCommandHandler(Function<C, CTX> fetchContext,
                                    Predicate<CTX> isIdempotent,
                                    Consumer<CTX> validate,
                                    Function<CTX, E> decide,
                                    EventListenerImpl eventListener) {

        this.fetchContext = fetchContext;
        this.isIdempotent = isIdempotent;
        this.validate = validate;
        this.decide = decide;
        this.eventListener = eventListener;
    }

    public Outcome handle(C command) {
        try {
            var context = fetchContext.apply(command);
            if (isIdempotent.test(context)) {
                return new Outcome.Ignore();
            }
            validate.accept(context);
            var event = decide.apply(context);
            eventListener.on(event);
            return new Outcome.Success();
        } catch (Exception e) {
            return new Outcome.Failure(Optional.ofNullable(e.getMessage()).orElse("An error occurred"));
        }
    }
}
