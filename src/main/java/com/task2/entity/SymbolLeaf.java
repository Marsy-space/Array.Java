package main.java.com.task2.entity;


import java.util.Collections;
import java.util.List;

public class SymbolLeaf implements TextComponent {
    private final char symbol;
    private final ComponentType type;

    public SymbolLeaf(char symbol, ComponentType type) {
        this.symbol = symbol;
        this.type = type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Cannot add to leaf");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Cannot remove from leaf");
    }

    @Override
    public List<TextComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String restore() {
        return String.valueOf(symbol);
    }
}