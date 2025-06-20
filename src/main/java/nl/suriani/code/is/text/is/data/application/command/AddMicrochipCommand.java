package nl.suriani.code.is.text.is.data.application.command;

import java.util.Objects;
import java.util.UUID;

public record AddMicrochipCommand(UUID idMicrochip,
                                  String namePet,
                                  UUID activeMode) implements Command {

    public AddMicrochipCommand {
        Objects.requireNonNull(idMicrochip);
        Objects.requireNonNull(namePet);
    }
}
