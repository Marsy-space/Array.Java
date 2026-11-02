package edu.kick.arrays.entity;

import edu.kick.arrays.observer.ArrayObserver;
import edu.kick.arrays.observer.ArraySubject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerArrayEntity implements ArraySubject {

  private final long id;
  private final String name;
  private final int[] values;
  private final List<ArrayObserver> observers = new ArrayList<>();

  public IntegerArrayEntity(long id, String name, int[] values) {
    this.id = id;
    this.name = name;
    this.values = values.clone();
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int[] getValues() {
    return values.clone();
  }

  public int getSize() {
    return values.length;
  }

  public void setValue(int index, int value) {
    values[index] = value;
    notifyObservers();
  }

  @Override
  public void attach(ArrayObserver observer) {
    observers.add(observer);
  }

  @Override
  public void detach(ArrayObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (ArrayObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public String toString() {
    return "IntegerArrayEntity{id=" + id + ", name=" + name
        + ", values=" + Arrays.toString(values) + "}";
  }
}
