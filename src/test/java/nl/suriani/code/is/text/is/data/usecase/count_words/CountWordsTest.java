package nl.suriani.code.is.text.is.data.usecase.count_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.CriteriaBuilder;
import nl.suriani.code.is.text.is.data.usecase.get_words.BaseUseCaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement.Separator.DOT;
import static org.assertj.core.api.Assertions.assertThat;

class CountWordsTest extends BaseUseCaseTest {

    private CountWords processor = new CountWords();

    @Test
    void emptyDocument_answerIs0() {
        var criteria = CriteriaBuilder
                .countWords()
                .build();

        var document = new Document(List.of());
        var result = processor.countWords(new CountWordsQuery(document, criteria));

        assertThat(result.isListOfWordsWithAmount()).isTrue();
        assertThat(result.asListOfWordsWithAmount().wordsWithAmount().isEmpty()).isTrue();
        assertThat(result.asListOfWordsWithAmount().totalAmount()).isEqualTo(0);
    }

    @Test
    void multipleSentenceS_duplicates() {
        var criteria = CriteriaBuilder
                .countWords()
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Hallo"),
                        word("Wereld"),
                        word("Hello"),
                        DOT
                )
        );

        var result = processor.countWords(new CountWordsQuery(document, criteria));

        assertThat(result.isListOfWordsWithAmount()).isTrue();
        assertThat(result.asListOfWordsWithAmount().totalAmount()).isEqualTo(6);
        assertThat(result.asListOfWordsWithAmount().wordsWithAmount().size()).isEqualTo(4);
        assertThat(result.asListOfWordsWithAmount().wordsWithAmount())
                .containsExactlyInAnyOrder(
                        wordWithAmount("hello", 2),
                        wordWithAmount("world", 2),
                        wordWithAmount("hallo", 1),
                        wordWithAmount("wereld", 1)
                );
        System.out.println(result);
    }

    @Test
    void countWords_whereEndsIn_rld() {
        var criteria = CriteriaBuilder
                .countWords()
                .whereWordEndsWith("rld")
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Hallo"),
                        word("Wereld"),
                        word("Hello"),
                        DOT
                )
        );

        var result = processor.countWords(new CountWordsQuery(document, criteria));

        assertThat(result.isListOfWordsWithAmount()).isTrue();
        assertThat(result.asListOfWordsWithAmount().totalAmount()).isEqualTo(2);
        assertThat(result.asListOfWordsWithAmount().wordsWithAmount().size()).isEqualTo(1);
        assertThat(result.asListOfWordsWithAmount().wordsWithAmount())
                .containsExactlyInAnyOrder(
                        wordWithAmount("world", 2)
                );
        System.out.println(result);
    }
}