package nl.suriani.code.is.text.is.data.usecase.get_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.Criteria;

import java.util.Objects;

public record GetWordsQuery(Document document, Criteria.GetWords criteria) {
    public GetWordsQuery {
        Objects.requireNonNull(document);
        Objects.requireNonNull(criteria);
    }
}
