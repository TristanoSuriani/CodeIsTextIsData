package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.application.usecase.Symbols;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.MicrochipId;

public record AddMicroChipUseCase() implements Expression<Nothing> {

    @Override
    public Nothing eval(Environment environment) {
        var petDoorRepository = environment.get(Symbols.PET_DOOR_REPOSITORY);
        var microchipId = environment.get(Symbols.MICROCHIP_ID);
        var namePet = environment.get(Symbols.NAME_PET);
        var activeMode = environment.get(Symbols.ACTIVE_MODE);

        return new MapTo<>(
                new SavePetDoor(
                    new AddMicrochip(
                        new FetchPetDoor(petDoorRepository::fetch),
                            new Literal<>(new Microchip(new MicrochipId(microchipId),
                                namePet,
                                activeMode))),

                petDoorRepository::save),

            petDoor -> new Nothing())
            .eval(environment);
    }
}
