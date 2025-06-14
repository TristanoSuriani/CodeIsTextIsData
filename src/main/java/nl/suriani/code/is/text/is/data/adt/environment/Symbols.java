package nl.suriani.code.is.text.is.data.adt.environment;

import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.model.ModeId;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;

public final class Symbols {
    public static final Symbol<PetDoorRepository> PET_DOOR_REPOSITORY =
            new Symbol<>("PET_DOOR_REPOSITORY", PetDoorRepository.class);

    public static final Symbol<MicrochipId> MICROCHIP_ID =
            new Symbol<>("MICROCHIP_ID", MicrochipId.class);

    public static final Symbol<String> NAME_PET =
            new Symbol<>("NAME_PET", String.class);

    public static final Symbol<ModeId> ACTIVE_MODE =
            new Symbol<>("ACTIVE_MODE_ID", ModeId.class);

    public static final Symbol<ModeId> FORCED_MODE_ID = new Symbol<>("FORCED_MODE_ID", ModeId.class);
}
