package nl.suriani.code.is.text.is.data.application;

import nl.suriani.code.is.text.is.data.application.aggregate.Lift;
import nl.suriani.code.is.text.is.data.application.command.Command;
import nl.suriani.code.is.text.is.data.application.event.Event;
import nl.suriani.code.is.text.is.data.application.runtime.Decider;

import java.util.List;

public class LiftDecider implements Decider<Command, Lift, Event> {
    @Override
    public Lift initialState() {
        return new Lift.Idle(0);
    }

    @Override
    public List<Event> decide(Command command, Lift state) {
        return List.of();
    }

    @Override
    public Lift evolve(Lift state, Event event) {
        return null;
    }

    @Override
    public boolean isTerminal(Lift state) {
        return false;
    }
}
