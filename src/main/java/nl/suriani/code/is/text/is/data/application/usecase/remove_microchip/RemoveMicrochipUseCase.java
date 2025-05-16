package nl.suriani.code.is.text.is.data.application.usecase.remove_microchip;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.application.command.RemoveMicrochipCommand;
import nl.suriani.code.is.text.is.data.application.usecase.EmptyResult;
import nl.suriani.code.is.text.is.data.application.usecase.UseCase;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;

import java.util.function.Supplier;

public class RemoveMicrochipUseCase implements UseCase<RemoveMicrochipCommand, EmptyResult> {
    private final Supplier<RemoveMicrochipCommand> commandSupplier;
    private final PetDoorRepository petDoorRepository;

    public RemoveMicrochipUseCase(Supplier<RemoveMicrochipCommand> commandSupplier, PetDoorRepository petDoorRepository) {
        this.commandSupplier = commandSupplier;
        this.petDoorRepository = petDoorRepository;
    }

    @Override
    public Expression<EmptyResult> define() {
        var command = commandSupplier.get();
        return new MapTo<>(
                new SavePetDoor(
                        new RemoveMicrochip(new FetchPetDoor(petDoorRepository::fetch),
                                new Literal<>(command.idMicrochip())),

                        petDoorRepository::save),

                petDoor -> new EmptyResult());
    }
}
