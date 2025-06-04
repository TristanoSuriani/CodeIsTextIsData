package nl.suriani.code.is.text.is.data.application.usecase;

import nl.suriani.code.is.text.is.data.model.ModeId;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;

import java.util.UUID;

public final class Symbols {
    public static final Symbol<PetDoorRepository> PET_DOOR_REPOSITORY =
            new Symbol<>("PET_DOOR_REPOSITORY", PetDoorRepository.class);

    public static final Symbol<UUID> MICROCHIP_ID =
            new Symbol<>("MICROCHIP_ID", UUID.class);

    public static final Symbol<String> NAME_PET =
            new Symbol<>("NAME_PET", String.class);

    public static final Symbol<ModeId> ACTIVE_MODE =
            new Symbol<>("ACTIVE_MODE", ModeId.class);
}
