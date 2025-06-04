package nl.suriani.code.is.text.is.data.analisys;

import nl.suriani.code.is.text.is.data.adt.*;
import nl.suriani.code.is.text.is.data.model.Microchip;
import nl.suriani.code.is.text.is.data.model.MicrochipId;
import nl.suriani.code.is.text.is.data.model.PetDoor;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExplainTest {
    Explain explain = new Explain();

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
}