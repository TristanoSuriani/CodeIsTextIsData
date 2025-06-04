package nl.suriani.code.is.text.is.data.model;

import java.util.*;

public record PetDoor(List<Microchip> microchips, ModeId defaultMode, List<Mode> modes) {
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

    public PetDoor(List<Microchip> microchips, ModeId defaultMode, List<Mode> modes) {
        Objects.requireNonNull(microchips);
        Objects.requireNonNull(defaultMode);
        Objects.requireNonNull(modes);
        this.microchips = List.copyOf(microchips);
        this.defaultMode = defaultMode;
        this.modes = List.copyOf(modes);
    }

    public PetDoor() {
        this(List.of(),
                DEFAULT_REGISTERED_CATS_MODE_ID,
                List.of(DEFAULT_REGISTERED_CATS_MODE, DEFAULT_FOREIGN_CATS_MODE));
    }

    public PetDoor addMicrochip(Microchip microchip) {
        Objects.requireNonNull(microchip);
        var microchips = new ArrayList<Microchip>(this.microchips);
        microchips.add(microchip);
        return new PetDoor(microchips, defaultMode, modes);
    }

    public PetDoor removeMicrochip(UUID idMicrochip) {
        Objects.requireNonNull(idMicrochip);
        return new PetDoor(microchips.stream()
                .filter(microchip -> !microchip.id().equals(idMicrochip))
                .toList(),
                defaultMode,
                modes);
    }
}
