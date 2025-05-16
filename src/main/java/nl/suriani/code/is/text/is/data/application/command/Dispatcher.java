package nl.suriani.code.is.text.is.data.application.command;

import nl.suriani.code.is.text.is.data.application.usecase.Result;
import nl.suriani.code.is.text.is.data.application.usecase.add_microchip.AddMicroChipUseCase;
import nl.suriani.code.is.text.is.data.application.usecase.remove_microchip.RemoveMicrochipUseCase;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;

public class Dispatcher {

    private final PetDoorRepository petDoorRepository;

    public Dispatcher(PetDoorRepository petDoorRepository) {
        this.petDoorRepository = petDoorRepository;
    }

    public Result dispatch(Command command) {
        var useCaseDefinition = switch (command) {
            case AddMicrochipCommand addMicroChipCommand -> new AddMicroChipUseCase(
                    () -> addMicroChipCommand,
                    petDoorRepository).define();

            case RemoveMicrochipCommand removeMicrochipCommand -> new RemoveMicrochipUseCase(
                    () -> removeMicrochipCommand,
                    petDoorRepository).define();
        };

        return useCaseDefinition.eval();
    }
}
