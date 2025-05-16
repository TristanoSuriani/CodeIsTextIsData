package nl.suriani.code.is.text.is.data.gadt;

import nl.suriani.code.is.text.is.data.model.PetDoor;

import java.util.Objects;
import java.util.function.Supplier;

public record FetchPetDoor(Supplier<PetDoor> fetch) implements Expression<PetDoor> {
    public FetchPetDoor {
        Objects.requireNonNull(fetch);
    }

    @Override
    public PetDoor eval() {
        return fetch.get();
    }

    @Override
    public String explain() {
        return "(FetchPetDoor)";
    }
}
