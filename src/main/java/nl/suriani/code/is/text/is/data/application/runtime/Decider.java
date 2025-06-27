package nl.suriani.code.is.text.is.data.application.runtime;

import java.util.List;

public interface Decider<C, S, E> {
    S initialState();
    List<E> decide(C command, S state);
    S evolve(S state, E event);
    boolean isTerminal(S state);
}
