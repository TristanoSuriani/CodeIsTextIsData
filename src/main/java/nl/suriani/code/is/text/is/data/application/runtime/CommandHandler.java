package nl.suriani.code.is.text.is.data.application.runtime;

import java.util.Optional;
import java.util.function.Supplier;

public class CommandHandler<C, S, E> {

    private final Decider<C, S, E> decider;
    private final EventListener<E> eventListener;

    public CommandHandler(Decider<C, S, E> decider, EventListener<E> eventListener) {
        this.decider = decider;
        this.eventListener = eventListener;
    }

    public Outcome handle(C command, Supplier<Optional<S>> stateSupplier) {
        try {
            var state = stateSupplier.get().orElse(decider.initialState());
            if (decider.isTerminal(state)) {
                return new Outcome.Failure("Cannot apply command to terminal state");
            }
            var events = decider.decide(command, state);

            var newState = events.stream()
                    .reduce(state,
                            decider::evolve,
                            (prev, next) -> next);

            events.forEach(eventListener::on);
            return new Outcome.Success();
        } catch (Exception e) {
            return new Outcome.Failure(Optional.ofNullable(e.getMessage()).orElse("An error occurred"));
        }
    }
}
