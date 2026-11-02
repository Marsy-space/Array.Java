package edu.kick.arrays.comparator;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.Comparator;

public class SizeComparator implements Comparator<IntegerArrayEntity> {

  @Override
  public int compare(IntegerArrayEntity first, IntegerArrayEntity second) {
    int firstSize = first.getSize();
    int secondSize = second.getSize();
    return Integer.compare(firstSize, secondSize);
  }
}
