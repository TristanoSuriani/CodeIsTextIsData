package nl.suriani.code.is.text.is.data.application.command;

import nl.suriani.code.is.text.is.data.model.ModeId;

import java.util.Objects;
import java.util.UUID;

public record AddMicrochipCommand(UUID idMicrochip,
                                  String namePet,
                                  ModeId activeMode) implements Command {

    public AddMicrochipCommand {
        Objects.requireNonNull(idMicrochip);
        Objects.requireNonNull(namePet);
    }
}
