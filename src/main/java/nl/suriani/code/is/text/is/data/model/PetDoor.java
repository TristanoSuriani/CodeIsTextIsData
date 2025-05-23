package nl.suriani.code.is.text.is.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record PetDoor(List<Microchip> microchips, ModeId defaultMode, List<Mode> modes) {
    public PetDoor {
        Objects.requireNonNull(microchips);
        microchips = List.copyOf(microchips);
    }

    public PetDoor addMicrochip(Microchip microchip) {
        Objects.requireNonNull(microchip);
        var microchips = new ArrayList<Microchip>(this.microchips);
        microchips.add(microchip);
        return new PetDoor(microchips);
    }

    public PetDoor removeMicrochip(UUID idMicrochip) {
        Objects.requireNonNull(idMicrochip);
        return new PetDoor(microchips.stream()
                .filter(microchip -> !microchip.id().equals(idMicrochip))
                .toList());
    }
}
