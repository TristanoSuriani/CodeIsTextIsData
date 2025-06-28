package nl.suriani.code.is.text.is.data.specification;

import nl.suriani.code.is.text.is.data.specification.action.Action;
import nl.suriani.code.is.text.is.data.specification.invariant.Invariant;

import java.util.ArrayList;
import java.util.List;

public record SpecificationRunner<C>(Action<C> init, Action<C> step, SpecificationOptions options, Invariant<C>... invariants) {

    public C run() {
        var context = init.execute(null);
        var stepCount = 1;
        var attemptCount = 1;
        boolean[] invariantsSatisfaction = initialiseInvariantSatisfactionVector();
        while (stepCount <= options.numberOfSteps() && attemptCount <= options.maxAttempts()) {
            try {
                context = step.execute(context);
                updateInvariantSatisfactionVector(context, invariantsSatisfaction);
                var failingInvariants = intermediateCheckInvariantSatisfactionVector(invariantsSatisfaction);
                if (!failingInvariants.isEmpty()) {
                    throw new IllegalStateException("Specification failed at step " + stepCount + ": " + failingInvariants.stream()
                            .map(Invariant::description)
                            .toList());
                }
                var debug = String.format("[%s/%s] - %s", stepCount, attemptCount, context);
                System.out.println(debug);
                stepCount++;
            } catch (Exception exception) {
                // perfectly fine to happen, just retry
            } finally {
                attemptCount++;
            }

        }
        var failingInvariants = finalCheckInvariantSatisfactionVector(invariantsSatisfaction);
        if (!failingInvariants.isEmpty()) {
            throw new IllegalStateException("Specification failed at step " + stepCount + ": " + failingInvariants.stream()
                    .map(Invariant::description)
                    .toList());
        }
        return context;
    }

    private boolean[] initialiseInvariantSatisfactionVector() {
        boolean[] invariantSatisfied = new boolean[invariants.length];
        for (int i = 0; i < invariantSatisfied.length; i++) {
            var invariant = invariants[i];
            invariantSatisfied[i] = switch (invariant.type()) {
                case SOMETIMES -> false;
                case ALWAYS -> true;
                case NEVER -> false;
            };
        }
        return invariantSatisfied;
    }

    private void updateInvariantSatisfactionVector(C context, boolean[] invariantSatisfaction) {
        for (int i = 0; i < invariantSatisfaction.length; i++) {
            var invariant = invariants[i];
            invariantSatisfaction[i] = switch (invariant.type()) {
                case SOMETIMES -> invariantSatisfaction[i] || invariant.isSatisfiedBy(context);
                case ALWAYS -> invariantSatisfaction[i] && invariant.isSatisfiedBy(context);
                case NEVER -> !invariantSatisfaction[i] || invariant.isSatisfiedBy(context);
            };
        }
    }

    private List<Invariant<C>> intermediateCheckInvariantSatisfactionVector(boolean[] invariantSatisfaction) {
        var failingInvariants = new ArrayList<Invariant<C>>();
        for (int i = 0; i < invariantSatisfaction.length; i++) {
            var invariant = invariants[i];
            if (!invariantSatisfaction[i]) {
                switch (invariant.type()) {
                    case ALWAYS, NEVER -> {
                        failingInvariants.add(invariant);
                    }
                    default -> {}
                }
            }
        }
        return failingInvariants;
    }

    private List<Invariant<C>> finalCheckInvariantSatisfactionVector(boolean[] invariantSatisfaction) {
        var failingInvariants = new ArrayList<Invariant<C>>();
        for (int i = 0; i < invariantSatisfaction.length; i++) {
            var invariant = invariants[i];
            if (!invariantSatisfaction[i]) {
                failingInvariants.add(invariant);
            }
        }
        return failingInvariants;
    }
}
