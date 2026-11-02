package main.java.com.task2.parser;

import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.exception.TextParseException;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text) throws TextParseException;
}