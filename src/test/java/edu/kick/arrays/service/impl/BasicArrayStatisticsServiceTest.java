package edu.kick.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.ArrayStatisticsService;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class BasicArrayStatisticsServiceTest {

  private static final IntegerArrayEntity FILLED = new IntegerArrayEntity(new int[] {4, 1, 7, 2});
  private static final IntegerArrayEntity EMPTY = new IntegerArrayEntity(new int[] {});
  private static final Optional<Integer> EXPECTED_MIN = Optional.of(1);
  private static final Optional<Integer> EXPECTED_MAX = Optional.of(7);
  private static final Optional<Long> EXPECTED_SUM = Optional.of(14L);
  private static final Optional<Double> EXPECTED_AVERAGE = Optional.of(3.5);

  private final ArrayStatisticsService service = new BasicArrayStatisticsService();

  @Test
  void findMinReturnsSmallestValue() {
    // given
    Optional<Integer> expected = EXPECTED_MIN;
    // when
    Optional<Integer> actual = service.findMin(FILLED);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void findMaxReturnsBiggestValue() {
    // given
    Optional<Integer> expected = EXPECTED_MAX;
    // when
    Optional<Integer> actual = service.findMax(FILLED);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void calculateSumReturnsSumOfElements() {
    // given
    Optional<Long> expected = EXPECTED_SUM;
    // when
    Optional<Long> actual = service.calculateSum(FILLED);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void calculateAverageReturnsMeanValue() {
    // given
    Optional<Double> expected = EXPECTED_AVERAGE;
    // when
    Optional<Double> actual = service.calculateAverage(FILLED);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void findMinReturnsEmptyOptionalForEmptyArray() {
    // given
    Optional<Integer> expected = Optional.empty();
    // when
    Optional<Integer> actual = service.findMin(EMPTY);
    // then
    assertEquals(expected, actual);
  }
}
