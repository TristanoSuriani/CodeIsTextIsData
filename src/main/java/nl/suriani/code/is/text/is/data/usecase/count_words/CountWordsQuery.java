package nl.suriani.code.is.text.is.data.usecase.count_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.Criteria;

import java.util.Objects;

public record CountWordsQuery(Document document, Criteria.CountWords criteria) {
    public CountWordsQuery {
        Objects.requireNonNull(document);
        Objects.requireNonNull(criteria);
    }
}