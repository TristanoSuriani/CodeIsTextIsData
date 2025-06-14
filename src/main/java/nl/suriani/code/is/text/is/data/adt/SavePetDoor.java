package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.model.PetDoor;

import java.util.Objects;
import java.util.function.Consumer;

public record SavePetDoor(Expression<PetDoor> petDoor, Consumer<PetDoor> consumer) implements Expression<PetDoor> {
    public SavePetDoor {
        Objects.requireNonNull(petDoor);
        Objects.requireNonNull(consumer);
    }

    @Override
    public PetDoor eval(Environment environment) {
        var petDoor = this.petDoor.eval(environment);
        consumer.accept(petDoor);
        return petDoor;
    }
}
