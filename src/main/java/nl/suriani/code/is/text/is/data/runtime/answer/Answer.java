package nl.suriani.code.is.text.is.data.runtime.answer;

import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;

import java.util.List;
import java.util.Objects;

public sealed interface Answer {

    record Empty() implements Answer {}

    record ListOfWords(List<SentenceElement.Word> words) implements Answer {
        public ListOfWords {
            Objects.requireNonNull(words);
            if (words.isEmpty()) {
                throw new IllegalArgumentException("Empty list of words");
            }
        }
    }

    default boolean isEmpty() {
        return this instanceof Empty;
    }

    default boolean isListOfWords() {
        return this instanceof ListOfWords;
    }

    default ListOfWords asListOfWords() {
        return (ListOfWords) this;
    }
}
