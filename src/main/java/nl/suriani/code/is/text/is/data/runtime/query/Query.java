package nl.suriani.code.is.text.is.data.runtime.query;

import java.util.Objects;
import java.util.Optional;

public record Query(SelectFromClause selectFromClause, Optional<WhereClause> whereClause, Optional<GroupByClause> groupByClause) {

    /*
        select (count)? word|sentence
            (
                where word|sentence is|starts with|ends with|contains $1
                (
                    group by word|sentence
                    (order by value|item_count ASC|DESC)?
                )?
            )?
            (order by word|sentence ASC|DESC)?
            (limit $2)?

        TODO group by is now fused with order by. split.
        TODO add limit
     */

    public Query {
        Objects.requireNonNull(selectFromClause);
        Objects.requireNonNull(whereClause);
        Objects.requireNonNull(groupByClause);
    }

    public static WithSelectFromClause selectWords() {
        return new WithSelectFromClause(new SelectFromClause(Subject.WORD, false));
    }

    public static WithSelectFromClause selectCountWords() {
        return new WithSelectFromClause(new SelectFromClause(Subject.WORD, true));
    }

    public static WithSelectFromClause selectSentences() {
        return new WithSelectFromClause(new SelectFromClause(Subject.SENTENCE, false));
    }

    public static WithSelectFromClause selectCountSentences() {
        return new WithSelectFromClause(new SelectFromClause(Subject.WORD, true));
    }

    record SelectFromClause(Subject subject, boolean count) {

        public SelectFromClause {
            Objects.requireNonNull(subject);
        }
    }

    record WhereClause(Subject subject, MatchType matchType, boolean affirmative, String parameter) {
        public WhereClause {
            Objects.requireNonNull(subject);
            Objects.requireNonNull(matchType);
            Objects.requireNonNull(parameter);
        }
    }

    record GroupByClause(Subject subject, Direction direction) {
        public GroupByClause {
            Objects.requireNonNull(subject);
            Objects.requireNonNull(direction);
        }
    }

    enum Subject {
        WORD,
        SENTENCE
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

    public record WithSelectFromClause(SelectFromClause selectFromClause) {
        public WithSelectFromClause {
            Objects.requireNonNull(selectFromClause);
        }

        public WithWhereClause whereWordIs(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.IS, true, parameter));
        }

        public WithWhereClause whereWordIsNot(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.IS, false, parameter));
        }

        public WithWhereClause whereWordContains(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.CONTAINS, true, parameter));
        }

        public WithWhereClause whereWordDoesNotContain(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.CONTAINS, false, parameter));
        }

        public WithWhereClause whereWordStartsWith(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.STARTS_WITH, true, parameter));
        }

        public WithWhereClause whereWordDoesNotStartWith(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.STARTS_WITH, false, parameter));
        }

        public WithWhereClause whereWordEndsWith(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.ENDS_WITH, true, parameter));
        }

        public WithWhereClause whereWordDoesNotEndWith(String parameter) {
            return new WithWhereClause(selectFromClause,
                    new WhereClause(Subject.WORD, MatchType.ENDS_WITH, false, parameter));
        }

        public Query build() {
            return new Query(selectFromClause, Optional.empty(), Optional.empty());
        }
    }

    public record WithWhereClause(SelectFromClause selectFromClause, WhereClause whereClause) {
        public WithWhereClause {
            Objects.requireNonNull(selectFromClause);
            Objects.requireNonNull(whereClause);
        }

        public WithGroupByClause groupByWordAsc() {
            return new WithGroupByClause(selectFromClause, whereClause, new GroupByClause(Subject.WORD, Direction.ASC));
        }

        public WithGroupByClause groupByWordDesc() {
            return new WithGroupByClause(selectFromClause, whereClause, new GroupByClause(Subject.WORD, Direction.DESC));
        }

        public WithGroupByClause groupBySentenceAsc() {
            return new WithGroupByClause(selectFromClause, whereClause, new GroupByClause(Subject.SENTENCE, Direction.ASC));
        }

        public WithGroupByClause groupBySentenceDesc() {
            return new WithGroupByClause(selectFromClause, whereClause, new GroupByClause(Subject.SENTENCE, Direction.DESC));
        }

        public Query build() {
            return new Query(selectFromClause, Optional.of(whereClause), Optional.empty());
        }
    }

    public record WithGroupByClause(SelectFromClause selectFromClause, WhereClause whereClause, GroupByClause groupByClause) {
        public WithGroupByClause {
            Objects.requireNonNull(selectFromClause);
            Objects.requireNonNull(whereClause);
            Objects.requireNonNull(groupByClause);
        }

        public Query build() {
            return new Query(selectFromClause, Optional.of(whereClause), Optional.of(groupByClause));
        }
    }
}
