package nl.suriani.code.is.text.is.data.usecase;

import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.command.CommandHandlerWithDecider;
import nl.suriani.code.is.text.is.data.application.command.Decider;
import nl.suriani.code.is.text.is.data.event.Event;
import nl.suriani.code.is.text.is.data.event.MicrochipAddedEvent;

import java.util.List;
import java.util.Optional;

public class AddMicrochip2 {
    private final MicrochipRepository microchipRepository;

    public AddMicrochip2(MicrochipRepository microchipRepository) {
        this.microchipRepository = microchipRepository;
    }

    public void handle(AddMicrochipCommand command) {
        var decider = new Decider<AddMicrochipCommand, AddMicrochip2.Microchip, Event>() {

            @Override
            public Microchip initialState() {
                return new NoMicrochip();
            }

            @Override
            public List<Event> decide(AddMicrochipCommand command, Microchip state) {
                return state instanceof AMicrochip
                        ? List.of()
                        : List.of(new MicrochipAddedEvent(command.idMicrochip(), command.namePet()));
            }

            @Override
            public Microchip evolve(Microchip state, Event event) {
                return switch (event) {
                    case MicrochipAddedEvent microchipAddedEvent -> new AMicrochip(microchipAddedEvent.microchipId().toString(),
                            microchipAddedEvent.namePet());

                    default -> state;
                };
            }

            @Override
            public boolean isTerminal(Microchip state) {
                return false;
            }
        };

        var commandHandler = new CommandHandlerWithDecider<AddMicrochipCommand, Microchip, Event>(
                decider,
                event -> {
                    if (event instanceof MicrochipAddedEvent microchipAddedEvent) {
                        System.out.println("Microchip added: " + microchipAddedEvent.microchipId() + ", Pet: " + microchipAddedEvent.namePet());
                    }
                }
        );

        commandHandler.handle(command, () -> Optional.ofNullable(microchipRepository.findById(command.idMicrochip().toString())));
    }

    public sealed interface Microchip {}
    public record NoMicrochip() implements AddMicrochip2.Microchip { }
    public record AMicrochip(String id, String namePet) implements AddMicrochip2.Microchip { }

    interface MicrochipRepository {
        Microchip findById(String id);
    }
}
