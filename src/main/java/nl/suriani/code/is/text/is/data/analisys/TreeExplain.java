package nl.suriani.code.is.text.is.data.analisys;

import nl.suriani.code.is.text.is.data.adt.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class TreeExplain implements Function<Expression<?>, String> {

    public String apply(Expression<?> expression) {
        return apply(expression, "", true);
    }

    private String apply(Expression<?> expression, String indent, boolean isLast) {
        Objects.requireNonNull(expression);

        var builder = new StringBuilder();
        var connector = isLast ? "└── " : "├── ";
        builder.append(indent).append(connector).append(label(expression)).append("\n");

        var children = childrenOf(expression);

        for (int i = 0; i < children.size(); i++) {
            var child = children.get(i);
            boolean last = (i == children.size() - 1);
            builder.append(apply(child, indent + (isLast ? "    " : "│   "), last));
        }

        return builder.toString();
    }

    private String label(Expression<?> expr) {
        return switch (expr) {
            case Literal<?> l -> "Literal <" + l.value().getClass().getSimpleName() + ">";
            case Nothing ignored -> "Nothing";
            case FetchPetDoor ignored -> "FetchPetDoor";
            case AddMicrochip ignored -> "AddMicrochip";
            case SavePetDoor ignored -> "SavePetDoor";
            case RemoveMicrochip ignored -> "RemoveMicrochip";
            case MapTo<?, ?> ignored -> "MapTo <Mapper>";
            default -> expr.getClass().getSimpleName();
        };
    }

    private List<Expression<?>> childrenOf(Expression<?> expr) {
        return switch (expr) {
            case AddMicrochip e -> List.of(e.petDoor(), e.microchip());
            case SavePetDoor e -> List.of(e.petDoor());
            case RemoveMicrochip e -> List.of(e.petDoor(), e.idMicrochip());
            case MapTo<?, ?> e -> List.of(e.expression());
            default -> List.of();
        };
    }
}