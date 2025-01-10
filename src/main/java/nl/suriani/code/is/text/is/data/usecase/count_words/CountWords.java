package nl.suriani.code.is.text.is.data.usecase.count_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;
import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

import java.util.List;
import java.util.stream.Collectors;

public class CountWords {

    public Answer.ListOfWordsWithAmount countWords(CountWordsQuery query) {
        var document = query.document();
        var wordsMap = extractWords(document).stream()
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.counting()));

        var wordsWithAmount = wordsMap.entrySet().stream()
                .map(entry -> new Answer.WordWithAmount(entry.getKey(), entry.getValue().intValue()))
                .toList();

        return new Answer.ListOfWordsWithAmount(wordsWithAmount);
    }

    private List<SentenceElement.Word> extractWords(Document document) {
        return document.sentences().stream()
                .flatMap(sentence -> sentence.elements().stream())
                .filter(SentenceElement::isWord)
                .map(SentenceElement::asWord)
                .map(word -> new SentenceElement.Word(word.value().toLowerCase()))
                .toList();
    }
}
