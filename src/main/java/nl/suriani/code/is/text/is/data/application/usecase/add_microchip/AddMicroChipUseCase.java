package nl.suriani.code.is.text.is.data.application.usecase.add_microchip;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.application.command.AddMicrochipCommand;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;
import nl.suriani.code.is.text.is.data.application.usecase.EmptyResult;
import nl.suriani.code.is.text.is.data.application.usecase.UseCase;

import java.util.function.Supplier;

public class AddMicroChipUseCase implements UseCase<AddMicrochipCommand, EmptyResult> {
    private final Supplier<AddMicrochipCommand> commandSupplier;
    private final PetDoorRepository petDoorRepository;

    public AddMicroChipUseCase(Supplier<AddMicrochipCommand> commandSupplier, PetDoorRepository petDoorRepository) {
        this.commandSupplier = commandSupplier;
        this.petDoorRepository = petDoorRepository;
    }

    @Override
    public Expression<EmptyResult> define() {
        var command = commandSupplier.get();
        return new MapTo<>(
                new SavePetDoor(
                        new AddMicrochip(new FetchPetDoor(petDoorRepository::fetch),
                                new Literal<>(new Microchip(command.idMicrochip(), command.namePet()))),

                        petDoorRepository::save),

                petDoor -> new EmptyResult());
    }
}
