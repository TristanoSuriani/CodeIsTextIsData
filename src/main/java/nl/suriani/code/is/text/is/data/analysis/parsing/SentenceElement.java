package nl.suriani.code.is.text.is.data.analysis.parsing;

import java.util.Objects;

public sealed interface SentenceElement {
    record Word(String value) implements SentenceElement {
        public Word {
            Objects.requireNonNull(value);
        }
    }
    
    enum Separator implements SentenceElement {
        COMMA(","),
        DOT("."),
        COLON(":"),
        SEMICOLON(";");

        private String value;

        Separator(String value) {
            this.value = value;
        }
    }

    default boolean isWord() {
        return this instanceof Word;
    }

    default Word asWord() {
        return (Word) this;
    }
}
