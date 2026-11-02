package main.java.com.task2.parser;

import main.java.com.task2.entity.ComponentType;
import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.entity.TextComposite;
import main.java.com.task2.exception.TextParseException;

public class SentenceToLexemeParser extends AbstractTextParser {
    private static final String LEXEME_REGEX = "\\s+";

    @Override
    public TextComponent parse(String text) throws TextParseException {
        if (text == null) throw new TextParseException("Text is null");
        
        TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
        sentence.setDelimiter(" ");
        
        String[] lexemes = text.split(LEXEME_REGEX);
        for (String lexeme : lexemes) {
            if (!lexeme.isEmpty() && nextParser != null) {
                sentence.add(nextParser.parse(lexeme));
            }
        }
        return sentence;
    }
}
