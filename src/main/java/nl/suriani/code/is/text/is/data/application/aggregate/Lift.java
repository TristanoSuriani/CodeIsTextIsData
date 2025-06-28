package nl.suriani.code.is.text.is.data.application.aggregate;

import java.util.Objects;

public interface Lift {

    Integer currentFloor();
    Lift newFloor(Integer currentFloor);
    
    sealed interface IdleOrGoingUp extends Lift {
        IdleOrGoingUp up(Integer targetFloor);
    }
    
    sealed interface IdleOrGoingDown extends Lift {
        IdleOrGoingDown down(Integer targetFloor);
    }

    record Idle(Integer currentFloor) implements IdleOrGoingUp, IdleOrGoingDown {
        public Idle {
            Objects.requireNonNull(currentFloor);
        }

        @Override
        public GoingUp up(Integer targetFloor) {
            return new GoingUp(currentFloor, targetFloor);
        }

        @Override
        public GoingDown down(Integer targetFloor) {
            return new GoingDown(currentFloor, targetFloor);
        }

        @Override
        public Lift newFloor(Integer currentFloor) {
            return new Idle(currentFloor);
        }
    }

    record GoingUp(Integer currentFloor, Integer targetFloor) implements IdleOrGoingUp {
        public GoingUp {
            Objects.requireNonNull(currentFloor);
            Objects.requireNonNull(targetFloor);

            if (targetFloor <= currentFloor) {
                throw new IllegalArgumentException("Target floor should be bigger than current floor");
            }
        }

        public GoingUp up(Integer targetFloor) {
            if (targetFloor <= this.targetFloor) {
                return this;
            }
            return new GoingUp(currentFloor, targetFloor);
        }

        @Override
        public IdleOrGoingUp newFloor(Integer currentFloor) {
            if (currentFloor >= targetFloor) {
                return new Idle(currentFloor);
            }
            return new GoingUp(currentFloor, targetFloor);
        }
    }

    record GoingDown(Integer currentFloor, Integer targetFloor) implements IdleOrGoingDown {
        public GoingDown {
            Objects.requireNonNull(currentFloor);
            Objects.requireNonNull(targetFloor);

            if (targetFloor >= currentFloor) {
                throw new IllegalArgumentException("Target floor should be smaller than current floor");
            }
        }

        @Override
        public IdleOrGoingDown down(Integer targetFloor) {
            if (targetFloor >= this.targetFloor) {
                return this;
            }
            return new GoingDown(currentFloor, targetFloor);
        }

        @Override
        public IdleOrGoingDown newFloor(Integer currentFloor) {
            if (currentFloor <= targetFloor) {
                return new Idle(currentFloor);
            }
            return new GoingDown(currentFloor, targetFloor);
        }
    }
}
