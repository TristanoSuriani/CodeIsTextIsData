package nl.suriani.code.is.text.is.data.application.command;

import java.util.Objects;
import java.util.UUID;

public record RemoveMicrochipCommand(UUID idMicrochip) implements Command {

    public RemoveMicrochipCommand {
        Objects.requireNonNull(idMicrochip);
    }
}
