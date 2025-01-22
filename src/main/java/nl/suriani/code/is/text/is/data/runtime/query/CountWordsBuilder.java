package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Optional;

public record CountWordsBuilder(Optional<Criteria.WhereClause> whereClause) {
    public CountWordsBuilder whereWordStartsWith(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, true, parameter)));
    }

    public CountWordsBuilder whereWordDoesNotStartWith(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, false, parameter)));
    }

    public CountWordsBuilder whereWordEndsWith(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, true, parameter)));
    }

    public CountWordsBuilder whereWordDoesNotEndWith(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, false, parameter)));
    }

    public CountWordsBuilder whereWordContains(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, true, parameter)));
    }

    public CountWordsBuilder whereWordDoesNotContain(String parameter) {
        return new CountWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, false, parameter)));
    }

    public Criteria.CountWords build() {
        return new Criteria.CountWords(whereClause);
    }
}
