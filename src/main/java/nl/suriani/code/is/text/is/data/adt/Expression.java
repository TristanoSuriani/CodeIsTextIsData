package nl.suriani.code.is.text.is.data.adt;

public sealed interface Expression<T> permits AddMicrochip, FetchPetDoor, Literal, MapTo, RemoveMicrochip, SavePetDoor {
    T eval(Environment environment);
    String explain();
}
