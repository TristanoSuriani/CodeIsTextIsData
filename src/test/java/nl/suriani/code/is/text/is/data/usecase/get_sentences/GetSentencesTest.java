package nl.suriani.code.is.text.is.data.usecase.get_sentences;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.runtime.query.CriteriaBuilder;
import nl.suriani.code.is.text.is.data.usecase.get_words.BaseUseCaseTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement.Separator.DOT;
import static org.assertj.core.api.Assertions.assertThat;

class GetSentencesTest  extends BaseUseCaseTest {

    private GetSentences processor = new GetSentences();

    @Test
    void emptyDocument_emptyAnswer() {
        var query = CriteriaBuilder
                .getSentences()
                .build();

        var document = new Document(List.of());
        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.asListOfSentences().sentences().isEmpty()).isTrue();
    }

    @Test
    void singleSentence_noDuplicates() {
        var query = CriteriaBuilder
                .getSentences()
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        DOT
                )
        );

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(word("hello"),
                        word("world"),
                        DOT)
                )
        );
    }

    @Test
    void singleSentence_duplicates() {
        var query = CriteriaBuilder
                .getSentences()
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        word("world"),
                        DOT
                )
        );

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(word("hello"),
                                word("world"),
                                word("world"),
                                DOT)
                )
        );
    }

    @Test
    void multipleSentenceS_duplicates() {
        var query = CriteriaBuilder
                .getSentences()
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

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(2);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
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
                )
        );
    }

    @Test
    void whereWordContains() {
        var query = CriteriaBuilder
                .getSentences()
                .whereWordContains("zz")
                .build();

        var document = document(
                sentence(
                        word("hello"),
                        word("World"),
                        word("world"),
                        DOT
                ),
                sentence(
                        word("Pineapple"),
                        word("on"),
                        word("pizza"),
                        word("is"),
                        word("forbidden"),
                        DOT
                )
        );

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(
                                word("Pineapple"),
                                word("on"),
                                word("pizza"),
                                word("is"),
                                word("forbidden"),
                                DOT
                        )
                )
        );
    }

    @Test
    void whereWordStartsWith() {
        var query = CriteriaBuilder
                .getSentences()
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

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(
                                word("Elly"),
                                word("Helli"),
                                word("world"),
                                DOT
                        )
                )
        );
    }

    @Test
    void whereWordEndsWith() {
        var query = CriteriaBuilder
                .getSentences()
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

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(
                                word("ORLY"),
                                word("Kano"),
                                word("world"),
                                DOT
                        )
                )
        );
    }

    @Test
    void whereWordDoesNotContain() {
        var query = CriteriaBuilder
                .getSentences()
                .whereWordDoesNotContain("eld")
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

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(word("hello"),
                                word("world"),
                                word("world"),
                                DOT)
                )
        );
    }

    @Test
    void whereWordDoesNotStartWith() {
        var query = CriteriaBuilder
                .getSentences()
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

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(
                                word("Wereld"),
                                DOT
                        )
                )
        );
    }

    @Test
    void whereWordDoesNotEndWith() {
        var query = CriteriaBuilder
                .getSentences()
                .whereWordDoesNotEndWith("o")
                .build();

        var document = document(
                sentence(
                        word("ORLYo"),
                        word("Kano"),
                        word("worldo"),
                        DOT
                ),
                sentence(
                        word("Wereld"),
                        DOT
                )
        );

        var result = processor.getSentences(new GetSentencesQuery(document, query));

        assertThat(result.isListOfSentences()).isTrue();
        assertThat(result.asListOfSentences().sentences().size()).isEqualTo(1);
        assertThat(result.asListOfSentences().sentences()).hasSameElementsAs(
                List.of(
                        sentence(
                                word("Wereld"),
                                DOT
                        )
                )
        );
    }
}