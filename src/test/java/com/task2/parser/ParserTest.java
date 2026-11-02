package com.task2.parser;


import com.task2.entity.TextComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private AbstractTextParser parser;

    @BeforeEach
    void setUp() {
        parser = new TextToParagraphParser();
        AbstractTextParser paragraphParser = new ParagraphToSentenceParser();
        AbstractTextParser sentenceParser = new SentenceToLexemeParser();
        AbstractTextParser lexemeParser = new LexemeToSymbolParser();
        
        parser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
    }

    @Test
    void testParseAndRestore() {
        String text = "Hello world. This is a test!";
        TextComponent component = parser.parse(text);
        // Восстановление может немного отличаться пробелами, но слова должны совпадать
        assertTrue(component.restore().contains("Hello world."));
        assertTrue(component.restore().contains("This is a test!"));
    }
}