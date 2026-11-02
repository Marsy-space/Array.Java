package edu.kick.arrays.service.impl;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.factory.ArrayEntityFactory;
import edu.kick.arrays.service.ArraySortService;

public class InsertionSortService implements ArraySortService {

  private final ArrayEntityFactory factory;

  public InsertionSortService(ArrayEntityFactory factory) {
    this.factory = factory;
  }

  @Override
  public IntegerArrayEntity sort(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    for (int index = 1; index < values.length; index++) {
      int current = values[index];
      int position = index - 1;
      while (position >= 0 && values[position] > current) {
        values[position + 1] = values[position];
        position--;
      }
      values[position + 1] = current;
    }
    String name = entity.getName();
    return factory.create(name, values);
  }
}
