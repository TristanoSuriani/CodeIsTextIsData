package nl.suriani.code.is.text.is.data.analysis.parsing;

import java.util.List;
import java.util.Objects;

public record Sentence(List<SentenceElement> elements) {
    public Sentence {
        Objects.requireNonNull(elements);
        if (elements.size() < 2) {
            throw new IllegalArgumentException("Sentence must contain at least two elements");
        }
    }
}
