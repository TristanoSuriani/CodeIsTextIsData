package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.model.PetDoor;

import java.util.Objects;

public record RemoveMicrochip(Expression<PetDoor> petDoor, Expression<MicrochipId> idMicrochip) implements Expression<PetDoor> {
    public RemoveMicrochip {
        Objects.requireNonNull(petDoor);
        Objects.requireNonNull(idMicrochip);
    }

    @Override
    public PetDoor eval(Environment environment) {
        return petDoor.eval(environment).removeMicrochip(idMicrochip.eval(environment));
    }
}
