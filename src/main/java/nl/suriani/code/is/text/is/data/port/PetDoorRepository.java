package nl.suriani.code.is.text.is.data.port;

import nl.suriani.code.is.text.is.data.model.PetDoor;

public interface PetDoorRepository {
    void save(PetDoor petDoor);

    PetDoor fetch();
}
