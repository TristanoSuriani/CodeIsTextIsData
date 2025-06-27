package nl.suriani.code.is.text.is.data.application.aggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LiftTest {

    @Test
    void test() {
        var lift_t1 = new Lift.Idle(0)
                .up(5)
                .newFloor(3)
                .newFloor(5);

        var lift_t2 = switch (lift_t1) {
            case Lift.Idle idle ->
                idle.down(3)
                        .newFloor(4)
                        .newFloor(3);
                default -> fail("Expected Idle");
        };

        assertInstanceOf(Lift.Idle.class, lift_t2);
    }
}