package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;

public sealed interface Expression<T> permits AddMicrochip, FetchPetDoor, Literal, MapTo, Nothing, RemoveMicrochip, SavePetDoor, SetForcedMode {
    T eval(Environment environment);
}
