package edu.kick.arrays.entity;

public class ArrayStatistics {

  private static final int HASH_MULTIPLIER = 31;

  private final int min;
  private final int max;
  private final long sum;
  private final double average;
  private final int count;

  public ArrayStatistics(int min, int max, long sum, double average, int count) {
    this.min = min;
    this.max = max;
    this.sum = sum;
    this.average = average;
    this.count = count;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public long getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  public int getCount() {
    return count;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof ArrayStatistics) {
      ArrayStatistics that = (ArrayStatistics) other;
      return min == that.min
          && max == that.max
          && sum == that.sum
          && Double.compare(average, that.average) == 0
          && count == that.count;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = min;
    result = HASH_MULTIPLIER * result + max;
    result = HASH_MULTIPLIER * result + Long.hashCode(sum);
    result = HASH_MULTIPLIER * result + Double.hashCode(average);
    result = HASH_MULTIPLIER * result + count;
    return result;
  }

  @Override
  public String toString() {
    return "ArrayStatistics{min=" + min + ", max=" + max + ", sum=" + sum
        + ", average=" + average + ", count=" + count + "}";
  }
}
