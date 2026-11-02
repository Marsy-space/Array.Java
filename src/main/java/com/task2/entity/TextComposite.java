package main.java.com.task2.entity;


import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final ComponentType type;
    private final List<TextComponent> children = new ArrayList<>();
    private String delimiter = ""; 

    public TextComposite(ComponentType type) {
        this.type = type;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        children.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String restore() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).restore());
            if (i < children.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}