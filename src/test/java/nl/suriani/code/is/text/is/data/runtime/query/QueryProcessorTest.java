package nl.suriani.code.is.text.is.data.runtime.query;

import nl.suriani.code.is.text.is.data.analysis.parsing.Document;
import nl.suriani.code.is.text.is.data.analysis.parsing.Sentence;
import nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nl.suriani.code.is.text.is.data.analysis.parsing.SentenceElement.Separator.*;
import static org.assertj.core.api.Assertions.assertThat;

class QueryProcessorTest {

    private final QueryProcessor processor  = new QueryProcessor();

    @Nested
    class TestSelectListOfWords {
        @Test
        void emptyDocument_emptyAnswer() {
            var query = Query.Builder
                    .getWords()
                    .build();

            var document = new Document(List.of());
            var result = processor.process(document, query);

            assertThat(result.isEmpty()).isTrue();
        }

        @Test
        void singleSentence_noDuplicates() {
            var query = Query.Builder
                    .getWords()
                    .build();

            var document = document(
                    sentence(
                            word("hello"),
                            word("World"),
                            DOT
                    )
            );

            var result = processor.process(document, query);

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
            var query = Query.Builder
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

            var result = processor.process(document, query);

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
            var query = Query.Builder
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

            var result = processor.process(document, query);

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
    }



    private SentenceElement.Word word(String word) {
        return new SentenceElement.Word(word);
    }

    private Sentence sentence(SentenceElement... elements) {
        return new Sentence(Arrays.asList(elements));
    }

    private Document document(Sentence... sentences) {
        return new Document(Arrays.asList(sentences));
    }
}