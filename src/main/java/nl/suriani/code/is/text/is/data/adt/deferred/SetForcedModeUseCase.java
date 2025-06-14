package nl.suriani.code.is.text.is.data.adt.deferred;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.adt.environment.Symbols;

public record SetForcedModeUseCase() implements DeferredExpression<Nothing> {

    @Override
    public Expression<Nothing> materialise(Environment environment) {
        var petDoorRepository = environment.get(Symbols.PET_DOOR_REPOSITORY);
        var forcedModeId = environment.get(Symbols.FORCED_MODE_ID);

        return new MapTo<>(
            new SavePetDoor(
                new SetForcedMode(
                    new FetchPetDoor(petDoorRepository::fetch),
                    new Literal<>(
                        forcedModeId)),

            petDoorRepository::save),

        petDoor -> new Nothing());
    }
}
