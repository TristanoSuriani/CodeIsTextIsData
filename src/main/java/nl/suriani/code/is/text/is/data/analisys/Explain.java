package nl.suriani.code.is.text.is.data.analisys;

import nl.suriani.code.is.text.is.data.adt.*;

import java.util.Objects;
import java.util.function.Function;

public class Explain implements Function<Expression<?>, String> {

    @Override
    public String apply(Expression<?> expression) {
        Objects.requireNonNull(expression);
        return switch (expression) {
            case AddMicrochip addMicrochip -> "(AddMicrochip " + apply(addMicrochip.petDoor()) + " " + apply(addMicrochip.microchip()) + ")";
            case FetchPetDoor fetchPetDoor -> "(FetchPetDoor)";
            case Literal<?> v -> "(Literal " + v.value().getClass().getSimpleName() + ")";
            case MapTo<?, ?> v -> "(MapTo " + apply(v.expression()) + " Mapper)";
            case Nothing nothing -> "(Nothing)";
            case RemoveMicrochip removeMicrochip -> ("RemoveMicrochip " + apply(removeMicrochip.petDoor()) + " " + apply(removeMicrochip.idMicrochip()) + ")");
            case SavePetDoor savePetDoor -> {
                var petDoor = apply(savePetDoor.petDoor());
                yield "(SavePetDoor " + petDoor + ")";
            }
            case SetForcedMode setForcedMode -> "(SetForcedMode " +
                    apply(setForcedMode.petDoor()) +
                    " " +
                    apply(setForcedMode.modeId()) +
                    ")";
        };
    }
}
