package nl.suriani.code.is.text.is.data.usecase.get_sentences;

import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

public class GetSentences {
    public Answer.ListOfSentences getSentences(GetSentencesQuery query) {
        var document = query.document();
        var predicate = query.criteria().asSentencePredicate();
        var sentences = document.extractSentencesWhere(predicate);

        return new Answer.ListOfSentences(sentences);
    }
}
