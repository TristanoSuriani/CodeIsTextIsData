package nl.suriani.code.is.text.is.data.analysis.parsing;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record Document(List<Sentence> sentences) {
    public Document {
        Objects.requireNonNull(sentences);
    }

    public List<SentenceElement.Word> extractWords() {
        return extractWordsWhere(t -> true);
    }

    public List<SentenceElement.Word> extractWordsWhere(Predicate<SentenceElement.Word> predicate) {
        return sentences().stream()
                .flatMap(sentence -> sentence.elements().stream())
                .filter(SentenceElement::isWord)
                .map(SentenceElement::asWord)
                .map(word -> new SentenceElement.Word(word.value().toLowerCase()))
                .filter(predicate)
                .toList();
    }

    public Map<SentenceElement.Word, Long> countWordsOccurrences() {
        return countWordsOccurrencesWhere(t -> true);
    }

    public Map<SentenceElement.Word, Long> countWordsOccurrencesWhere(Predicate<SentenceElement.Word> predicate) {
        return extractWords().stream()
                .filter(predicate)
                .collect(Collectors.groupingBy(word -> word,
                        Collectors.counting()));
    }

    public List<Sentence> extractSentencesWhere(Predicate<Sentence> predicate) {
        return sentences().stream()
                .filter(predicate)
                .toList();
    }

    public List<Sentence> extractSentences() {
        return extractSentencesWhere(t -> true);
    }
}
