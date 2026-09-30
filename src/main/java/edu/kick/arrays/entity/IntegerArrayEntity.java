package edu.kick.arrays.entity;

import java.util.Arrays;

public class IntegerArrayEntity {

  private final int[] values;

  public IntegerArrayEntity(int[] values) {
    this.values = values.clone();
  }

  public int[] getValues() {
    return values.clone();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof IntegerArrayEntity) {
      IntegerArrayEntity that = (IntegerArrayEntity) other;
      return Arrays.equals(values, that.values);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(values);
  }

  @Override
  public String toString() {
    return "IntegerArrayEntity" + Arrays.toString(values);
  }
}
