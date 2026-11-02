package edu.kick.arrays.comparator;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.Comparator;

public class IdComparator implements Comparator<IntegerArrayEntity> {

  @Override
  public int compare(IntegerArrayEntity first, IntegerArrayEntity second) {
    long firstId = first.getId();
    long secondId = second.getId();
    return Long.compare(firstId, secondId);
  }
}
