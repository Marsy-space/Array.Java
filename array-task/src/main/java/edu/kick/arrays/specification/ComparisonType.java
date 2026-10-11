package edu.kick.arrays.specification;

public enum ComparisonType {
  GREATER,
  LESS,
  EQUAL;

  public boolean matches(double actual, double expected) {
    int result = Double.compare(actual, expected);
    return switch (this) {
      case GREATER -> result > 0;
      case LESS -> result < 0;
      case EQUAL -> result == 0;
    };
  }
}
