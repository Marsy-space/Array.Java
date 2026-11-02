package main.java.com.task2.service;


import main.java.com.task2.entity.ComponentType;
import main.java.com.task2.entity.TextComponent;
import java.util.*;

public class TextService {

    // Подсчет количества букв и символов
    public int[] countLettersAndSymbols(TextComponent text) {
        int letters = 0;
        int symbols = 0;
        List<TextComponent> allSymbols = getAllLeaves(text);
        
        for (TextComponent c : allSymbols) {
            if (c instanceof main.java.com.task2.entity.SymbolLeaf) {
                if (c.getType() == ComponentType.SYMBOL) letters++;
                else if (c.getType() == ComponentType.PUNCTUATION) symbols++;
            }
        }
        return new int[]{letters, symbols};
    }

    // Задача 1: Найти наибольшее количество предложений, в которых есть одинаковые слова
    public int findMaxSentencesWithSameWord(TextComponent text) {
        List<TextComponent> sentences = getComponentsByType(text, ComponentType.SENTENCE);
        Map<String, Integer> wordToSentenceCount = new HashMap<>();

        for (TextComponent sentence : sentences) {
            Set<String> uniqueWordsInSentence = new HashSet<>();
            for (TextComponent lexeme : getComponentsByType(sentence, ComponentType.LEXEME)) {
                String word = lexeme.restore().toLowerCase().replaceAll("[^a-zа-я0-9]", "");
                if (!word.isEmpty()) {
                    uniqueWordsInSentence.add(word);
                }
            }
            for (String word : uniqueWordsInSentence) {
                wordToSentenceCount.put(word, wordToSentenceCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordToSentenceCount.values().stream().max(Integer::compare).orElse(0);
    }

    // Задача 2: Вывести предложения в порядке возрастания количества заданной буквы
    public List<String> sortSentencesByLetterCount(TextComponent text, char letter) {
        List<TextComponent> sentences = getComponentsByType(text, ComponentType.SENTENCE);
        String target = String.valueOf(Character.toLowerCase(letter));

        return sentences.stream()
                .sorted(Comparator.comparingInt(s -> countLetterInComponent(s, target)))
                .map(TextComponent::restore)
                .toList();
    }

    // Задача 3: В каждом предложении поменять местами первую и последнюю лексему
    public void swapFirstAndLastLexeme(TextComponent text) {
        List<TextComponent> sentences = getComponentsByType(text, ComponentType.SENTENCE);
        for (TextComponent sentence : sentences) {
            List<TextComponent> lexemes = sentence.getChildren();
            if (lexemes.size() > 1) {
                TextComponent first = lexemes.get(0);
                TextComponent last = lexemes.get(lexemes.size() - 1);
                
                // Используем методы класса-композита для замены
                sentence.remove(first);
                sentence.remove(last);
                
                // Вставляем в правильном порядке (костыль из-за того, что у нас List, а не LinkedList с addFirst/addLast)
                List<TextComponent> currentChildren = sentence.getChildren();
                currentChildren.add(0, last);
                currentChildren.add(first);
                
                // Очищаем и восстанавливаем
                while (!sentence.getChildren().isEmpty()) {
                    sentence.remove(sentence.getChildren().get(0));
                }
                for (TextComponent c : currentChildren) {
                    sentence.add(c);
                }
            }
        }
    }

    // --- Вспомогательные методы ---
    private List<TextComponent> getAllLeaves(TextComponent component) {
        List<TextComponent> result = new ArrayList<>();
        if (component.getType() == ComponentType.SYMBOL || component.getType() == ComponentType.PUNCTUATION) {
            result.add(component);
        } else {
            for (TextComponent child : component.getChildren()) {
                result.addAll(getAllLeaves(child));
            }
        }
        return result;
    }

    private List<TextComponent> getComponentsByType(TextComponent root, ComponentType type) {
        List<TextComponent> result = new ArrayList<>();
        if (root.getType() == type) {
            result.add(root);
        }
        for (TextComponent child : root.getChildren()) {
            result.addAll(getComponentsByType(child, type));
        }
        return result;
    }

    private int countLetterInComponent(TextComponent component, String target) {
        return (int) getAllLeaves(component).stream()
                .filter(c -> c.restore().toLowerCase().equals(target))
                .count();
    }
}
