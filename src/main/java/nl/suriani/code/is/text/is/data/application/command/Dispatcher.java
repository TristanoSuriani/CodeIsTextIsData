package nl.suriani.code.is.text.is.data.application.command;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.adt.deferred.AddMicroChipUseCase;
import nl.suriani.code.is.text.is.data.adt.deferred.RemoveMicroChipUseCase;
import nl.suriani.code.is.text.is.data.adt.environment.Symbols;
import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;

public class Dispatcher {

    private final PetDoorRepository petDoorRepository;

    public Dispatcher(PetDoorRepository petDoorRepository) {
        this.petDoorRepository = petDoorRepository;
    }

    public void dispatch(Command command) {
        var environment = new Environment();
        environment.put(Symbols.PET_DOOR_REPOSITORY, petDoorRepository);

        var useCase = switch (command) {
            case AddMicrochipCommand addMicroChipCommand -> {
                environment.put(Symbols.MICROCHIP_ID, new MicrochipId(addMicroChipCommand.idMicrochip()));
                environment.put(Symbols.NAME_PET, addMicroChipCommand.namePet());
                environment.put(Symbols.ACTIVE_MODE, addMicroChipCommand.activeMode());

                yield new AddMicroChipUseCase();
            }

            case RemoveMicrochipCommand removeMicrochipCommand -> {
                environment.put(Symbols.MICROCHIP_ID, removeMicrochipCommand.idMicrochip());
                yield new RemoveMicroChipUseCase();
            }
        };

        var result = useCase.eval(environment);
        System.out.println(result);
    }
}
