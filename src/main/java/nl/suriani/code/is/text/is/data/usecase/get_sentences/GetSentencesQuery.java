package nl.suriani.code.is.text.is.data.usecase.get_sentences;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.Criteria;

import java.util.Objects;

public record GetSentencesQuery(Document document, Criteria.GetSentences criteria) {
    public GetSentencesQuery {
        Objects.requireNonNull(document);
        Objects.requireNonNull(criteria);
    }
}
