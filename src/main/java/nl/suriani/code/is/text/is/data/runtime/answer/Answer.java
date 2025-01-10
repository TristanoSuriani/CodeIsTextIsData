package nl.suriani.code.is.text.is.data.runtime.answer;

import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;

import java.util.List;
import java.util.Objects;

public sealed interface Answer {

    record ListOfWords(List<SentenceElement.Word> words) implements Answer {
        public ListOfWords {
            Objects.requireNonNull(words);
        }
    }

    record ListOfWordsWithAmount(List<WordWithAmount> wordsWithAmount, int totalAmount) implements Answer {

        public ListOfWordsWithAmount {
            Objects.requireNonNull(wordsWithAmount);
            if (wordsWithAmount.stream().mapToInt(WordWithAmount::amount).sum() != totalAmount) {
                throw new IllegalArgumentException("Total amount must be the sum of all amounts");
            }
        }

        public ListOfWordsWithAmount(List<WordWithAmount> wordsWithAmount) {
            this(wordsWithAmount, wordsWithAmount.stream().mapToInt(WordWithAmount::amount).sum());
        }
    }

    record WordWithAmount(SentenceElement.Word word, int amount) {

        public WordWithAmount {
            Objects.requireNonNull(word);
            if (amount < 1) {
                throw new IllegalArgumentException("Amount must be greater than 0");
            }
        }
    }

    default boolean isListOfWords() {
        return this instanceof ListOfWords;
    }

    default boolean isListOfWordsWithAmount() {
        return this instanceof ListOfWordsWithAmount;
    }

    default ListOfWords asListOfWords() {
        return (ListOfWords) this;
    }

    default ListOfWordsWithAmount asListOfWordsWithAmount() {
        return (ListOfWordsWithAmount) this;
    }
}
