package nl.suriani.code.is.text.is.data.usecase;

import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.command.EventBasedCommandHandler;
import nl.suriani.code.is.text.is.data.application.command.Outcome;
import nl.suriani.code.is.text.is.data.event.EventListenerImpl;
import nl.suriani.code.is.text.is.data.event.MicrochipAddedEvent;

public class AddMicrochip {
    private final EventListenerImpl eventListener;
    private final MicrochipRepository microchipRepository;

    public AddMicrochip(EventListenerImpl eventListener, MicrochipRepository microchipRepository) {
        this.eventListener = eventListener;
        this.microchipRepository = microchipRepository;
    }

    public Outcome execute(AddMicrochipCommand command) {
        var commandHandler = new EventBasedCommandHandler<AddMicrochipCommand, Microchip, MicrochipAddedEvent>(
            c -> microchipRepository.findById(c.idMicrochip().toString()),
            ctx -> ctx instanceof AMicrochip,
            ignored -> {},
            ignored -> new MicrochipAddedEvent(command.idMicrochip(), command.namePet()),
            eventListener
        );

        return commandHandler.handle(command);
    }

    public sealed interface Microchip {}
    public record NoMicrochip() implements Microchip { }
    public record AMicrochip(String id, String namePet) implements Microchip { }

    interface MicrochipRepository {
        Microchip findById(String id);
    }
}
