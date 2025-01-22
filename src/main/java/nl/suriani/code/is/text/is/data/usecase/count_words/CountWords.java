package nl.suriani.code.is.text.is.data.usecase.count_words;

import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

public class CountWords {

    public Answer.ListOfWordsWithAmount countWords(CountWordsQuery query) {
        var document = query.document();
        var wordsOccurrences = document.countWordsOccurrencesWhere(query.criteria().asWordPredicate());
        var wordsWithAmount = wordsOccurrences.entrySet().stream()
                .map(entry -> new Answer.WordWithAmount(entry.getKey(), entry.getValue().intValue()))
                .toList();

        return new Answer.ListOfWordsWithAmount(wordsWithAmount);
    }
}
