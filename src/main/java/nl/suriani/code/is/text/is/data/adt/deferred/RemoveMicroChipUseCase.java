package nl.suriani.code.is.text.is.data.adt.deferred;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.application.usecase.Symbols;

public record RemoveMicroChipUseCase() implements DeferredExpression<Nothing> {

    @Override
    public Expression<Nothing> materialise(Environment environment) {
        var petDoorRepository = environment.get(Symbols.PET_DOOR_REPOSITORY);
        var microchipId = environment.get(Symbols.MICROCHIP_ID);

        return new MapTo<>(
            new SavePetDoor(
                new RemoveMicrochip(
                    new FetchPetDoor(petDoorRepository::fetch),
                        new Literal<>(
                            microchipId)),

            petDoorRepository::save),

        petDoor -> new Nothing());
    }
}
