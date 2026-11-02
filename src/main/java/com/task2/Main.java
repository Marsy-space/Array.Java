package main.java.com.task2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.task2.entity.TextComponent;
import main.java.com.task2.parser.*;
import main.java.com.task2.service.TextService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Path.of("src/main/resources/data/input.txt"));

        // Сборка Chain of Responsibility
        AbstractTextParser parser = new TextToParagraphParser();
        AbstractTextParser paragraphParser = new ParagraphToSentenceParser();
        AbstractTextParser sentenceParser = new SentenceToLexemeParser();
        AbstractTextParser lexemeParser = new LexemeToSymbolParser();

        parser.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);

        TextComponent textComponent = parser.parse(text);
        TextService service = new TextService();

        System.out.println("--- Восстановленный текст ---");
        System.out.println(textComponent.restore());

        System.out.println("\n--- Подсчет букв и символов ---");
        int[] counts = service.countLettersAndSymbols(textComponent);
        System.out.println("Букв: " + counts[0] + ", Символов: " + counts[1]);

        System.out.println("\n--- Задача 1: Макс. предложений с одинаковыми словами ---");
        System.out.println(service.findMaxSentencesWithSameWord(textComponent));

        System.out.println("\n--- Задача 2: Сортировка по букве 'a' ---");
        List<String> sorted = service.sortSentencesByLetterCount(textComponent, 'a');
        sorted.forEach(System.out::println);

        System.out.println("\n--- Задача 3: Поменять местами первую и последнюю лексему ---");
        service.swapFirstAndLastLexeme(textComponent);
        System.out.println(textComponent.restore());
    }
}