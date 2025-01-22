package nl.suriani.code.is.text.is.data.usecase.get_words;

import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

import java.util.List;

public class GetWords {
    public Answer.ListOfWords getWords(GetWordsQuery query) {
        var document = query.document();
        if (document.sentences().isEmpty()) {
            return new Answer.ListOfWords(List.of());
        }

        var predicate = query.criteria().asWordPredicate();
        var words = document.extractWordsWhere(predicate).stream()
                .distinct()
                .toList();

        return new Answer.ListOfWords(words);
    }
}
