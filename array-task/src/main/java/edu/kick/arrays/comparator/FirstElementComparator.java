package edu.kick.arrays.comparator;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.Comparator;

public class FirstElementComparator implements Comparator<IntegerArrayEntity> {

  private static final int EMPTY_ARRAY_KEY = Integer.MIN_VALUE;

  @Override
  public int compare(IntegerArrayEntity first, IntegerArrayEntity second) {
    int firstKey = keyOf(first);
    int secondKey = keyOf(second);
    return Integer.compare(firstKey, secondKey);
  }

  private int keyOf(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    if (values.length > 0) {
      return values[0];
    }
    return EMPTY_ARRAY_KEY;
  }
}
