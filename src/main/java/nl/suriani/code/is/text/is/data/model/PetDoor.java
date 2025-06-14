package nl.suriani.code.is.text.is.data.model;

import java.util.*;

public record PetDoor(List<Microchip> microchips, ModeId defaultMode,
                      Optional<ModeId> forcedMode,
                      List<Mode> modes) {

    public static final ModeId DEFAULT_REGISTERED_CATS_MODE_ID =
            new ModeId(UUID.nameUUIDFromBytes("DefaultRegisteredCatsMode".getBytes()));

    public static final Mode DEFAULT_REGISTERED_CATS_MODE =
            new Mode(DEFAULT_REGISTERED_CATS_MODE_ID, "Default Registered Cats Mode",
                    new Rule(RuleType.ALWAYS, Optional.empty(), Optional.empty()),
                    new Rule(RuleType.NEVER, Optional.empty(), Optional.empty()));

    public static final ModeId DEFAULT_FOREIGN_CATS_MODE_ID =
            new ModeId(UUID.nameUUIDFromBytes("DefaultForeignCatsMode".getBytes()));

    public static final Mode DEFAULT_FOREIGN_CATS_MODE =
            new Mode(DEFAULT_FOREIGN_CATS_MODE_ID, "Default Foreign Cats Mode",
                    new Rule(RuleType.NEVER, Optional.empty(), Optional.empty()),
                    new Rule(RuleType.ALWAYS, Optional.empty(), Optional.empty()));

    public PetDoor(List<Microchip> microchips, ModeId defaultMode, Optional<ModeId> forcedMode, List<Mode> modes) {
        Objects.requireNonNull(microchips);
        Objects.requireNonNull(defaultMode);
        Objects.requireNonNull(forcedMode);
        Objects.requireNonNull(modes);
        this.microchips = List.copyOf(microchips);
        this.defaultMode = defaultMode;
        this.forcedMode = forcedMode;
        this.modes = List.copyOf(modes);
    }

    public PetDoor() {
        this(List.of(),
                DEFAULT_REGISTERED_CATS_MODE_ID,
                Optional.empty(),
                List.of(DEFAULT_REGISTERED_CATS_MODE, DEFAULT_FOREIGN_CATS_MODE));
    }

    public PetDoor addMicrochip(Microchip microchip) {
        Objects.requireNonNull(microchip);
        var microchips = new ArrayList<>(this.microchips);
        microchips.add(microchip);
        return new PetDoor(microchips, defaultMode, forcedMode, modes);
    }

    public PetDoor removeMicrochip(MicrochipId microchipId) {
        Objects.requireNonNull(microchipId);
        return new PetDoor(microchips.stream()
                .filter(microchip -> !microchip.id().equals(microchipId))
                .toList(),
                defaultMode,
                forcedMode,
                modes);
    }

    public PetDoor setForcedMode(ModeId modeId) {
        Objects.requireNonNull(modeId);
        return new PetDoor(microchips, defaultMode, Optional.of(modeId), modes);
    }
}
