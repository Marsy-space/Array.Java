package main.java.com.task2.parser;

import main.java.com.task2.entity.ComponentType;
import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.entity.TextComposite;
import main.java.com.task2.exception.TextParseException;

public class TextToParagraphParser extends AbstractTextParser {
    private static final String PARAGRAPH_REGEX = "\\n\\s*"; // Разделение по переносам строк

    @Override
    public TextComponent parse(String text) throws TextParseException {
        if (text == null) {
            throw new TextParseException("Text is null");
        }
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        textComposite.setDelimiter("\n");
        
        String[] paragraphs = text.split(PARAGRAPH_REGEX);
        for (String p : paragraphs) {
            if (!p.trim().isEmpty()) {
                if (nextParser != null) {
                    textComposite.add(nextParser.parse(p.trim()));
                }
            }
        }
        return textComposite;
    }
}
