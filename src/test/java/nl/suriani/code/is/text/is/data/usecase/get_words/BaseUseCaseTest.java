package nl.suriani.code.is.text.is.data.usecase.get_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.analysis.parsing.Sentence;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;
import nl.suriani.code.is.text.is.data.runtime.answer.Answer;

import java.util.Arrays;

public class BaseUseCaseTest {
    protected SentenceElement.Word word(String word) {
        return new SentenceElement.Word(word);
    }

    protected Sentence sentence(SentenceElement... elements) {
        return new Sentence(Arrays.asList(elements));
    }

    protected Document document(Sentence... sentences) {
        return new Document(Arrays.asList(sentences));
    }

    protected Answer.WordWithAmount wordWithAmount(String value, int amount) {
        return new Answer.WordWithAmount(word(value), amount);
    }
}
