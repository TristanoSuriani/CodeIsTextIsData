package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Objects;
import java.util.Optional;

public sealed interface Query {


    record Builder() {
        static GetWordsBuilder getWords() {
            return new GetWordsBuilder(Optional.empty(), Optional.empty());
        }
    }

    record GetWords(Optional<WhereClause> whereClause, Optional<OrderByClause> orderByClause) implements Query {

        public GetWords {
            Objects.requireNonNull(whereClause);
            Objects.requireNonNull(orderByClause);
        }
    }

    record CountWords(Optional<WhereClause> whereClause) implements Query {

        public CountWords {
            Objects.requireNonNull(whereClause);
        }
    }

    record GetSentences(Optional<WhereClause> whereClause) implements Query {

        public GetSentences {
            Objects.requireNonNull(whereClause);
        }
    }

    record GetWordsBuilder(Optional<WhereClause> whereClause, Optional<OrderByClause> orderByClause) {
        public GetWordsBuilder whereWordStartsWith(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.STARTS_WITH, true, parameter)),
                    Optional.empty());
        }

        public GetWordsBuilder whereWordDoesNotStartWith(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.STARTS_WITH, false, parameter)),
                    Optional.empty());
        }

        public GetWordsBuilder whereWordEndsWith(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.ENDS_WITH, true, parameter)),
                    Optional.empty());
        }

        public GetWordsBuilder whereWordDoesNotEndWith(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.ENDS_WITH, false, parameter)),
                    Optional.empty());
        }

        public GetWordsBuilder whereWordContains(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.CONTAINS, true, parameter)),
                    Optional.empty());
        }

        public GetWordsBuilder whereWordDoesNotContain(String parameter) {
            return new GetWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.CONTAINS, false, parameter)),
                    Optional.empty());
        }

        public GetWords build() {
            return new GetWords(whereClause, orderByClause);
        }
    }

    record CountWordsBuilder(Optional<WhereClause> whereClause) {
        public CountWordsBuilder whereWordStartsWith(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.STARTS_WITH, true, parameter)));
        }

        public CountWordsBuilder whereWordDoesNotStartWith(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.STARTS_WITH, false, parameter)));
        }

        public CountWordsBuilder whereWordEndsWith(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.ENDS_WITH, true, parameter)));
        }

        public CountWordsBuilder whereWordDoesNotEndWith(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.ENDS_WITH, false, parameter)));
        }

        public CountWordsBuilder whereWordContains(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.CONTAINS, true, parameter)));
        }

        public CountWordsBuilder whereWordDoesNotContain(String parameter) {
            return new CountWordsBuilder(
                    Optional.of(new WhereClause(Subject.WORD, MatchType.CONTAINS, false, parameter)));
        }

        public CountWords build() {
            return new CountWords(whereClause);
        }
    }

    record WhereClause(Subject subject, MatchType matchType, boolean affirmative, String parameter) {
        public WhereClause {
            Objects.requireNonNull(subject);
            Objects.requireNonNull(matchType);
            Objects.requireNonNull(parameter);
        }
    }

    record OrderByClause(Subject subject, Direction direction) {
        public OrderByClause {
            Objects.requireNonNull(subject);
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
        IS,
        STARTS_WITH,
        ENDS_WITH,
        CONTAINS,
    }

    enum Direction {
        ASC,
        DESC
    }
}
