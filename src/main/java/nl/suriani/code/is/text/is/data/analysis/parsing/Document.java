package nl.suriani.code.is.text.is.data.analysis.parsing;

import java.util.List;
import java.util.Objects;

public record Document(List<Sentence> sentences) {
    public Document {
        Objects.requireNonNull(sentences);
    }
}
