package nl.suriani.code.is.text.is.data.adt;

import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.model.ModeId;
import nl.suriani.code.is.text.is.data.model.PetDoor;

public record SetForcedMode(Expression<PetDoor> petDoor,
                            Expression<ModeId> modeId) implements Expression<PetDoor> {
    @Override
    public PetDoor eval(Environment environment) {
        return petDoor.eval(environment).setForcedMode(modeId.eval(environment));
    }
}
