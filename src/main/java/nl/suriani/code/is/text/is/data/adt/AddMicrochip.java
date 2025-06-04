package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.PetDoor;

import java.util.Objects;

public record AddMicrochip(Expression<PetDoor> petDoor, Expression<Microchip> microchip) implements Expression<PetDoor> {
    public AddMicrochip {
        Objects.requireNonNull(petDoor);
        Objects.requireNonNull(microchip);
    }

    @Override
    public PetDoor eval(Environment environment) {
        return petDoor.eval(environment).addMicrochip(microchip.eval(environment));
    }

    @Override
    public String explain() {
        return "(AddMicrochip " + petDoor.explain() + " " + microchip.explain() + ")";
    }
}
