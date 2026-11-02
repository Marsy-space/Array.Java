package main.java.com.task2.parser;


import main.java.com.task2.entity.ComponentType;
import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.entity.TextComposite;
import main.java.com.task2.exception.TextParseException;

public class ParagraphToSentenceParser extends AbstractTextParser {
    // Регулярка: предложение заканчивается на . ! ? возможно с кавычками или скобками после
    private static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";

    @Override
    public TextComponent parse(String text) throws TextParseException {
        if (text == null) throw new TextParseException("Text is null");
        
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        paragraph.setDelimiter(" ");
        
        String[] sentences = text.split(SENTENCE_REGEX);
        for (String s : sentences) {
            if (!s.trim().isEmpty() && nextParser != null) {
                paragraph.add(nextParser.parse(s.trim()));
            }
        }
        return paragraph;
    }
}