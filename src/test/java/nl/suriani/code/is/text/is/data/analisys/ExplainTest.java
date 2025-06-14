package nl.suriani.code.is.text.is.data.analisys;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.adt.deferred.AddMicroChipUseCase;
import nl.suriani.code.is.text.is.data.adt.deferred.RemoveMicroChipUseCase;
import nl.suriani.code.is.text.is.data.adt.deferred.SetForcedModeUseCase;
import nl.suriani.code.is.text.is.data.adt.environment.Environment;
import nl.suriani.code.is.text.is.data.adt.environment.Symbols;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.model.ModeId;
import nl.suriani.code.is.text.is.data.model.PetDoor;
import nl.suriani.code.is.text.is.data.port.PetDoorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ExplainTest {

    @Mock
    private PetDoorRepository petDoorRepository;

    Explain explain = new Explain();
    TreeExplain treeExplain = new TreeExplain();

    @Test
    void test() {
        var expression =  new MapTo<>(
                new SavePetDoor(
                        new AddMicrochip(
                                new FetchPetDoor(() -> null),
                                new Literal<>(new Microchip(new MicrochipId(UUID.randomUUID()),
                                        "Cake",
                                        PetDoor.DEFAULT_REGISTERED_CATS_MODE_ID))),

                        ignored -> {}),

                petDoor -> new Nothing());

        System.out.println(explain.apply(expression));
    }

    @Test
    void test2() {
        var expression = new AddMicroChipUseCase();
        var environment = new Environment();
        environment.put(Symbols.PET_DOOR_REPOSITORY, petDoorRepository);
        environment.put(Symbols.MICROCHIP_ID, new MicrochipId(UUID.randomUUID()));
        environment.put(Symbols.NAME_PET, "Cake");
        environment.put(Symbols.ACTIVE_MODE, PetDoor.DEFAULT_REGISTERED_CATS_MODE_ID);

        System.out.println(explain.apply(expression.materialise(environment)));
        System.out.println(treeExplain.apply(expression.materialise(environment)));

        var expression2 = new RemoveMicroChipUseCase();
        environment.put(Symbols.PET_DOOR_REPOSITORY, petDoorRepository);
        System.out.println(explain.apply(expression2.materialise(environment)));
        System.out.println(treeExplain.apply(expression2.materialise(environment)));
    }

    @Test
    void setForcedMode() {

        var expression1 = new SetForcedModeUseCase();
        var environment = new Environment();
        environment.put(Symbols.PET_DOOR_REPOSITORY, petDoorRepository);
        environment.put(Symbols.FORCED_MODE_ID, new ModeId(UUID.randomUUID()));
        System.out.println(explain.apply(expression1.materialise(environment)));
        System.out.println(treeExplain.apply(expression1.materialise(environment)));

    }
}