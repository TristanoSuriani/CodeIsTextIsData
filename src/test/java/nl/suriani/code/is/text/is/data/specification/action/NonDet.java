package nl.suriani.code.is.text.is.data.specification.action;

import java.util.Arrays;
import java.util.List;

public interface NonDet {
    static <T> T oneOf(T... values) {
        return oneOf(Arrays.asList(values));
    }

    static <T> T oneOf(List<T> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be provided");
        }
        return values.get((int) (Math.random() * values.size()));
    }
}
