package edu.kick.arrays.service.impl;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.factory.ArrayEntityFactory;
import edu.kick.arrays.service.ArraySortService;

public class BubbleSortService implements ArraySortService {

  private final ArrayEntityFactory factory;

  public BubbleSortService(ArrayEntityFactory factory) {
    this.factory = factory;
  }

  @Override
  public IntegerArrayEntity sort(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    int length = values.length;
    for (int pass = 0; pass < length - 1; pass++) {
      for (int index = 0; index < length - pass - 1; index++) {
        if (values[index] > values[index + 1]) {
          int buffer = values[index];
          values[index] = values[index + 1];
          values[index + 1] = buffer;
        }
      }
    }
    return factory.create(values);
  }
}
