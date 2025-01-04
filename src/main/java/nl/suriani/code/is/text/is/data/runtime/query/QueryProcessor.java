package nl.suriani.code.is.text.is.data.runtime.query;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;
import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

public class QueryProcessor {
    public Answer process(Document document, Query query) {
        if (document.sentences().isEmpty()) {
            return new Answer.Empty();
        }

        var words = document.sentences().stream()
                .flatMap(sentence -> sentence.elements().stream())
                .filter(SentenceElement::isWord)
                .map(SentenceElement::asWord)
                .map(word -> new SentenceElement.Word(word.value().toLowerCase()))
                .distinct()
                .toList();

        return new Answer.ListOfWords(words);
    }
}
