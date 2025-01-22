package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Optional;

public record GetWordsBuilder(Optional<Criteria.WhereClause> whereClause,
                              Optional<Criteria.SortedClause> sortedClause) {
    public GetWordsBuilder whereWordStartsWith(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, true, parameter)),
                Optional.empty());
    }

    public GetWordsBuilder whereWordDoesNotStartWith(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, false, parameter)),
                Optional.empty());
    }

    public GetWordsBuilder whereWordEndsWith(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, true, parameter)),
                Optional.empty());
    }

    public GetWordsBuilder whereWordDoesNotEndWith(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, false, parameter)),
                Optional.empty());
    }

    public GetWordsBuilder whereWordContains(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, true, parameter)),
                Optional.empty());
    }

    public GetWordsBuilder whereWordDoesNotContain(String parameter) {
        return new GetWordsBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, false, parameter)),
                Optional.empty());
    }

    public Criteria.GetWords build() {
        return new Criteria.GetWords(whereClause, sortedClause);
    }
}
