package nl.suriani.code.is.text.is.data.usecase;

import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.command.CommandHandlerWithDecider;
import nl.suriani.code.is.text.is.data.event.EventListenerImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class AddMicrochipTest {
    @Test
    void addMicrochip() {
        var command = new AddMicrochipCommand(UUID.randomUUID(), "Dog", UUID.randomUUID());
        var useCase = new AddMicrochip(new EventListenerImpl(), new AddMicrochip.MicrochipRepository() {
            @Override
            public AddMicrochip.Microchip findById(String id) {
                return new AddMicrochip.NoMicrochip();
            }
        });
        var outcome = useCase.execute(command);
        System.out.println(outcome);
    }

    @Test
    void addMicrochip2() {
        var command = new AddMicrochipCommand(UUID.randomUUID(), "Dog", UUID.randomUUID());
        var useCase = new AddMicrochip2(id -> new AddMicrochip2.NoMicrochip());
        useCase.handle(command);
    }
}