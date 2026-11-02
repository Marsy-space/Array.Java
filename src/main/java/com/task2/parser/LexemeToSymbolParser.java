package main.java.com.task2.parser;

import main.java.com.task2.entity.ComponentType;
import main.java.com.task2.entity.SymbolLeaf;
import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.entity.TextComposite;
import main.java.com.task2.exception.TextParseException;

public class LexemeToSymbolParser extends AbstractTextParser {
    private static final String PUNCTUATION_REGEX = "[^a-zA-Z0-9]";

    @Override
    public TextComponent parse(String text) throws TextParseException {
        if (text == null) throw new TextParseException("Text is null");
        
        TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
        lexeme.setDelimiter("");
        
        char[] chars = text.toCharArray();
        for (char c : chars) {
            ComponentType type = String.valueOf(c).matches(PUNCTUATION_REGEX) 
                    ? ComponentType.PUNCTUATION 
                    : ComponentType.SYMBOL;
            lexeme.add(new SymbolLeaf(c, type));
        }
        return lexeme;
    }
}