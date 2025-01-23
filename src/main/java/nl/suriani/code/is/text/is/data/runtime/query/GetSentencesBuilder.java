package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Optional;

public record GetSentencesBuilder(Optional<Criteria.WhereClause> whereClause) {
    public GetSentencesBuilder whereWordStartsWith(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, true, parameter)));
    }

    public GetSentencesBuilder whereWordDoesNotStartWith(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.STARTS_WITH, false, parameter)));
    }

    public GetSentencesBuilder whereWordEndsWith(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, true, parameter)));
    }

    public GetSentencesBuilder whereWordDoesNotEndWith(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.ENDS_WITH, false, parameter)));
    }

    public GetSentencesBuilder whereWordContains(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, true, parameter)));
    }

    public GetSentencesBuilder whereWordDoesNotContain(String parameter) {
        return new GetSentencesBuilder(
                Optional.of(new Criteria.WhereClause(Criteria.Subject.WORD, Criteria.MatchType.CONTAINS, false, parameter)));
    }

    public Criteria.GetSentences build() {
        return new Criteria.GetSentences(whereClause);
    }
}
