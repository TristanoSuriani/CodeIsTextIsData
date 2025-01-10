package nl.suriani.code.is.text.is.data.usecase.get_words;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.Criteria;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement.Separator.DOT;
import static org.assertj.core.api.Assertions.assertThat;

class GetWordsTest extends BaseUseCaseTest {
    
    private GetWords processor = new GetWords();
    
    @Test
    void emptyDocument_emptyAnswer() {
        var query = Criteria.Builder
                .getWords()
                .build();

        var document = new Document(List.of());
        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.asListOfWords().words().isEmpty()).isTrue();
    }

    @Test
    void singleSentence_noDuplicates() {
        var query = Criteria.Builder
                .getWords()
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(2);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("hello"),
                        word("world")
                )
        );
    }

    @Test
    void singleSentence_duplicates() {
        var query = Criteria.Builder
                .getWords()
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        word("world"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(2);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("hello"),
                        word("world")
                )
        );
    }

    @Test
    void multipleSentenceS_duplicates() {
        var query = Criteria.Builder
                .getWords()
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

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(4);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("hello"),
                        word("world"),
                        word("hallo"),
                        word("wereld")
                )
        );
    }

    @Test
    void whereWordContains() {
        var query = Criteria.Builder
                .getWords()
                .whereWordContains("ll")
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

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(2);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("hello"),
                        word("hallo")
                )
        );
    }

    @Test
    void whereWordStartsWith() {
        var query = Criteria.Builder
                .getWords()
                .whereWordStartsWith("e")
                .build();

        var document = document(
                sentence(
                        word("Elly"),
                        word("Helli"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Wereld"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(1);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("elly")
                )
        );
    }

    @Test
    void whereWordEndsWith() {
        var query = Criteria.Builder
                .getWords()
                .whereWordEndsWith("o")
                .build();

        var document = document(
                sentence(
                        word("ORLY"),
                        word("Kano"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Wereld"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(1);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("kano")
                )
        );
    }

    @Test
    void whereWordDoesNotContain() {
        var query = Criteria.Builder
                .getWords()
                .whereWordDoesNotContain("ll")
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

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(2);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("world"),
                        word("wereld")
                )
        );
    }

    @Test
    void whereWordDoesNotStartWith() {
        var query = Criteria.Builder
                .getWords()
                .whereWordDoesNotStartWith("e")
                .build();

        var document = document(
                sentence(
                        word("Elly"),
                        word("Helli"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Wereld"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(3);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("helli"),
                        word("world"),
                        word("wereld")
                )
        );
    }

    @Test
    void whereWordDoesNotEndWith() {
        var query = Criteria.Builder
                .getWords()
                .whereWordDoesNotEndWith("o")
                .build();

        var document = document(
                sentence(
                        word("ORLY"),
                        word("Kano"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Wereld"),
                        DOT
                )
        );

        var result = processor.getWords(new GetWordsQuery(document, query));

        assertThat(result.isListOfWords()).isTrue();
        assertThat(result.asListOfWords().words().size()).isEqualTo(3);
        assertThat(result.asListOfWords().words()).hasSameElementsAs(
                List.of(
                        word("orly"),
                        word("wereld"),
                        word("world")
                )
        );
    }
}