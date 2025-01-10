package nl.suriani.code.is.text.is.data.usecase.get_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;
import nl.suriani.code.is.text.is.data.runtime.answer.Answer;
import nl.suriani.code.is.text.is.data.runtime.query.Criteria;

import java.util.List;
import java.util.function.Predicate;

public class GetWords {
    public Answer.ListOfWords getWords(GetWordsQuery query) {
        var document = query.document();
        if (document.sentences().isEmpty()) {
            return new Answer.ListOfWords(List.of());
        }

        var words = extractWords(document).stream()
                .filter(getPredicate(query))
                .distinct()
                .toList();

        return new Answer.ListOfWords(words);
    }

    private List<SentenceElement.Word> extractWords(Document document) {
        return document.sentences().stream()
                .flatMap(sentence -> sentence.elements().stream())
                .filter(SentenceElement::isWord)
                .map(SentenceElement::asWord)
                .map(word -> new SentenceElement.Word(word.value().toLowerCase()))
                .toList();
    }

    private Predicate<SentenceElement.Word> getPredicate(GetWordsQuery query) {
        var maybeWhereClause = query.criteria().whereClause();
        if (maybeWhereClause.isEmpty()) {
            return w -> true;
        }

        var whereClause = maybeWhereClause.get();

        var basePredicate = getBasePredicate(whereClause);

        return whereClause.affirmative()
                ? basePredicate
                : basePredicate.negate();
    }

    private static Predicate<SentenceElement.Word> getBasePredicate(Criteria.WhereClause whereClause) {
        return switch (whereClause.matchType()) {
            case IS -> null;
            case STARTS_WITH -> w -> w.asWord().value().startsWith(whereClause.parameter());
            case ENDS_WITH -> w -> w.asWord().value().endsWith(whereClause.parameter());
            case CONTAINS -> w -> w.asWord().value().contains(whereClause.parameter());
        };
    }
}
