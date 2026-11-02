package edu.kick.arrays.specification;

import edu.kick.arrays.entity.IntegerArrayEntity;

public class SizeSpecification implements ArraySpecification {

  private final ComparisonType comparisonType;
  private final int expected;

  public SizeSpecification(ComparisonType comparisonType, int expected) {
    this.comparisonType = comparisonType;
    this.expected = expected;
  }

  @Override
  public boolean isSatisfiedBy(IntegerArrayEntity entity) {
    int size = entity.getSize();
    return comparisonType.matches(size, expected);
  }
}
