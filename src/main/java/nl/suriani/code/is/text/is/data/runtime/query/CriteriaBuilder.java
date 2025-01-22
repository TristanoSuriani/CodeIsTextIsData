package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Optional;

public record CriteriaBuilder() {
    public static GetWordsBuilder getWords() {
        return new GetWordsBuilder(Optional.empty(), Optional.empty());
    }

    public static CountWordsBuilder countWords() {
        return new CountWordsBuilder(Optional.empty());
    }
}
