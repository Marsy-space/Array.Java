package com.task2.servise;

import com.task2.entity.TextComponent;
import com.task2.parser.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TextServiceTest {
    private TextService service;
    private TextComponent textComponent;

    @BeforeEach
    void setUp() {
        service = new TextService();
        AbstractTextParser parser = new TextToParagraphParser();
        AbstractTextParser paragraphParser = new ParagraphToSentenceParser();
        AbstractTextParser sentenceParser = new SentenceToLexemeParser();
        AbstractTextParser lexemeParser = new LexemeToSymbolParser();
        
        parser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        
        String testText = "A b c. A b d. E f g.";
        textComponent = parser.parse(testText);
    }

    @Test
    void testCountLettersAndSymbols() {
        int[] counts = service.countLettersAndSymbols(textComponent);
        assertEquals(11, counts[0]); // Буквы a,b,c,a,b,d,e,f,g
        assertEquals(3, counts[1]);  // Точки
    }

    @Test
    void testFindMaxSentencesWithSameWord() {
        // "A" встречается в 2 предложениях, "b" в 2 предложениях
        assertEquals(2, service.findMaxSentencesWithSameWord(textComponent));
    }

    @Test
    void testSortSentencesByLetterCount() {
        List<String> sorted = service.sortSentencesByLetterCount(textComponent, 'a');
        // В 1-м и 2-м предложениях по одной 'a', в третьем 0.
        assertEquals(0, sorted.get(0).toLowerCase().chars().filter(ch -> ch == 'a').count()); // E f g.
    }

    @Test
    void testSwapFirstAndLastLexeme() {
        service.swapFirstAndLastLexeme(textComponent);
        String restored = textComponent.restore();
        assertTrue(restored.contains("c. b A")); // c. b A (было A b c.)
    }
}