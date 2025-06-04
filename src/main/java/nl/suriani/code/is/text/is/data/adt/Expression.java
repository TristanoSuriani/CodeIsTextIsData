package nl.suriani.code.is.text.is.data.adt;

public sealed interface Expression<T> permits AddMicroChipUseCase, AddMicrochip, FetchPetDoor, Literal, MapTo, Nothing, RemoveMicrochip, SavePetDoor {
    T eval(Environment environment);
}
