package edu.kick.arrays.comparator;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.Comparator;

public class NameComparator implements Comparator<IntegerArrayEntity> {

  @Override
  public int compare(IntegerArrayEntity first, IntegerArrayEntity second) {
    String firstName = first.getName();
    String secondName = second.getName();
    return firstName.compareTo(secondName);
  }
}
