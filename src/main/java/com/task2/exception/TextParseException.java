package main.java.com.task2.exception;

public class TextParseException extends RuntimeException {
    public TextParseException(String message) {
        super(message);
    }
    public TextParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
