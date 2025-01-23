package nl.suriani.code.is.text.is.data.runtime.query;

import nl.suriani.code.is.text.is.data.analysis.parsing.Sentence;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public sealed interface Criteria {

    Optional<WhereClause> whereClause();

    default Predicate<SentenceElement.Word> asWordPredicate() {
        return whereClause()
                .map(WhereClause::asWordPredicate)
                .orElse(t -> true);
    }

    default Predicate<Sentence> asSentencePredicate() {
        return whereClause()
                .map(WhereClause::asSentencePredicate)
                .orElse(t -> true);
    }

    record GetWords(Optional<WhereClause> whereClause, Optional<SortedClause> sortedClause) implements Criteria {

        public GetWords {
            Objects.requireNonNull(whereClause);
            Objects.requireNonNull(sortedClause);
        }
    }

    record CountWords(Optional<WhereClause> whereClause) implements Criteria {

        public CountWords {
            Objects.requireNonNull(whereClause);
        }
    }

    record GetSentences(Optional<WhereClause> whereClause) implements Criteria {

        public GetSentences {
            Objects.requireNonNull(whereClause);
        }

    }

    record WhereClause(Subject subject, MatchType matchType, boolean affirmative, String parameter) {
        public WhereClause {
            Objects.requireNonNull(subject);
            Objects.requireNonNull(matchType);
            Objects.requireNonNull(parameter);
        }

        public Predicate<SentenceElement.Word> asWordPredicate() {

            Predicate<SentenceElement.Word> basePredicate = switch (matchType) {
                case STARTS_WITH -> w -> w.asWord().value().startsWith(parameter.toLowerCase());
                case ENDS_WITH -> w -> w.asWord().value().endsWith(parameter.toLowerCase());
                case CONTAINS -> w -> w.asWord().value().contains(parameter.toLowerCase());
            };

            return affirmative
                    ? basePredicate
                    : basePredicate.negate();
        }

        public Predicate<Sentence> asSentencePredicate() {
            Predicate<Sentence> basePredicate = switch (matchType) {
                case STARTS_WITH -> s -> s.elements().stream()
                        .filter(SentenceElement::isWord)
                        .anyMatch(w -> w.asWord().value().startsWith(parameter.toLowerCase()));

                case ENDS_WITH -> s -> s.elements().stream()
                        .filter(SentenceElement::isWord)
                        .anyMatch(w -> w.asWord().value().endsWith(parameter.toLowerCase()));

                case CONTAINS -> s -> s.elements().stream()
                        .filter(SentenceElement::isWord)
                        .anyMatch(w -> w.asWord().value().contains(parameter.toLowerCase()));
            };

            return affirmative
                    ? basePredicate
                    : basePredicate.negate();
        }
    }

    record SortedClause(Direction direction) {
        public SortedClause {
            Objects.requireNonNull(direction);
        }
    }

    enum Subject {
        WORD,
        SENTENCE,
        VALUE,
        ITEM_COUNT
    }

    enum MatchType {
        STARTS_WITH,
        ENDS_WITH,
        CONTAINS,
    }

    enum Direction {
        ASC,
        DESC
    }
}
