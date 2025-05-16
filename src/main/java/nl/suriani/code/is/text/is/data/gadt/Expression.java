package nl.suriani.code.is.text.is.data.gadt;

public sealed interface Expression<T> permits AddMicrochip, FetchPetDoor, Literal, MapTo, RemoveMicrochip, SavePetDoor {
    T eval();
    String explain();
}
