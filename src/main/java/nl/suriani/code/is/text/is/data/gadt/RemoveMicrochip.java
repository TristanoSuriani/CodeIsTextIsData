package nl.suriani.code.is.text.is.data.gadt;

import nl.suriani.code.is.text.is.data.model.PetDoor;

import java.util.Objects;
import java.util.UUID;

public record RemoveMicrochip(Expression<PetDoor> petDoor, Expression<UUID> idMicrochip) implements Expression<PetDoor> {
    public RemoveMicrochip {
        Objects.requireNonNull(petDoor);
        Objects.requireNonNull(idMicrochip);
    }

    @Override
    public PetDoor eval() {
        return petDoor.eval().removeMicrochip(idMicrochip.eval());
    }

    @Override
    public String explain() {
        return "(RemoveMicrochip " + petDoor.explain() +
                " " + idMicrochip.explain() + ")";
    }
}
