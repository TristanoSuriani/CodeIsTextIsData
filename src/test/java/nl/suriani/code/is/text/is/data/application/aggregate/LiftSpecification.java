package nl.suriani.code.is.text.is.data.application.aggregate;

import nl.suriani.code.is.text.is.data.specification.SpecificationOptions;
import nl.suriani.code.is.text.is.data.specification.SpecificationRunner;
import nl.suriani.code.is.text.is.data.specification.action.Action;
import nl.suriani.code.is.text.is.data.specification.action.Any;
import nl.suriani.code.is.text.is.data.specification.action.NonDet;
import nl.suriani.code.is.text.is.data.specification.invariant.Invariants;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LiftSpecification {
    @Test
    void test() {
        var allowedFloors = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8);
        var init = Action.<Lift>define(
                "init",
                ignored -> new Lift.Idle(0));

        var goUp = Action.<Lift>define(
                "goUp",
                lift -> switch (lift) {
                    case Lift.Idle idle -> idle.up(NonDet.oneOf(allowedFloors));
                    case Lift.GoingUp up -> up.up(NonDet.oneOf(allowedFloors));
                    default -> throw new IllegalStateException("Unexpected value: " + lift);
                });

        var goDown = Action.<Lift>define(
                "goDown",
                lift -> switch (lift) {
                    case Lift.Idle idle -> idle.down(NonDet.oneOf(allowedFloors));
                    case Lift.GoingDown down -> down.down(NonDet.oneOf(allowedFloors));
                    default -> throw new IllegalStateException("Unexpected value: " + lift);
                });

        var reachFloor = Action.<Lift>define(
                "reachFloor",
                lift -> switch (lift) {
                    case Lift.Idle idle -> idle.newFloor(NonDet.oneOf(allowedFloors));
                    case Lift.GoingUp up -> up.newFloor(NonDet.oneOf(allowedFloors));
                    case Lift.GoingDown down -> down.newFloor(NonDet.oneOf(allowedFloors));
                    default -> throw new IllegalStateException("Unexpected value: " + lift);
                });

        var step = new Any<>(init, goUp, goDown, reachFloor);

        var neverCurrentFloorSameAsTargetFloor = Invariants.<Lift>never(
                "Lift current floor should never be the same as target floor",
                lift -> switch (lift) {
                    case Lift.GoingUp up -> up.currentFloor().equals(up.targetFloor());
                    case Lift.GoingDown down -> down.currentFloor().equals(down.targetFloor());
                    default -> false;
                });

        var sometimesLiftIsIdle = Invariants.<Lift>sometimes(
                "Lift should be idle sometimes",
                lift -> switch (lift) {
                    case Lift.Idle ignored -> true;
                    default -> false;
                });

        var sometimesLiftGoesUp = Invariants.<Lift>sometimes(
                "Lift should go up sometimes",
                lift -> switch (lift) {
                    case Lift.GoingUp ignored -> true;
                    default -> false;
                });

        var sometimesLiftGoesDown = Invariants.<Lift>sometimes(
                "Lift should go down sometimes",
                lift -> switch (lift) {
                    case Lift.GoingDown ignored -> true;
                    default -> false;
                });

        var whenGoingUpTargetFloorIsAlwaysHigherThanCurrentFloor = Invariants.<Lift>always(
                "Lift going up should always have target floor higher than current floor",
                lift -> switch (lift) {
                    case Lift.GoingUp up -> up.targetFloor() > up.currentFloor();
                    default -> true;
                });

        var whenGoingDownTargetFloorIsAlwaysSmallerThanCurrentFloor = Invariants.<Lift>always(
                "Lift going down should always have target floor smaller than current floor",
                lift -> switch (lift) {
                    case Lift.GoingDown down -> down.targetFloor() < down.currentFloor();
                    default -> true;
                });

        var runner = new SpecificationRunner<Lift>(
                init,
                step,
                new SpecificationOptions(50, 100),
                neverCurrentFloorSameAsTargetFloor,
                sometimesLiftIsIdle,
                sometimesLiftGoesUp,
                sometimesLiftGoesDown,
                whenGoingUpTargetFloorIsAlwaysHigherThanCurrentFloor,
                whenGoingDownTargetFloorIsAlwaysSmallerThanCurrentFloor
        );

        runner.run();
    }
}
