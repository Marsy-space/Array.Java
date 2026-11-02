package edu.kick.arrays.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.ArrayStatisticsService;
import edu.kick.arrays.service.impl.BasicArrayStatisticsService;
import org.junit.jupiter.api.Test;

class ArraySpecificationTest {

  private static final IntegerArrayEntity ENTITY =
      new IntegerArrayEntity(5L, "five", new int[] {1, 2, 3});

  private final ArrayStatisticsService statisticsService = new BasicArrayStatisticsService();

  @Test
  void idSpecificationAcceptsSameId() {
    // given
    ArraySpecification specification = new IdSpecification(5L);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertTrue(actual);
  }

  @Test
  void idSpecificationRejectsOtherId() {
    // given
    ArraySpecification specification = new IdSpecification(6L);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertFalse(actual);
  }

  @Test
  void nameSpecificationAcceptsSameName() {
    // given
    ArraySpecification specification = new NameSpecification("five");
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertTrue(actual);
  }

  @Test
  void sizeSpecificationAcceptsEqualSize() {
    // given
    ArraySpecification specification = new SizeSpecification(ComparisonType.EQUAL, 3);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertTrue(actual);
  }

  @Test
  void statisticSpecificationAcceptsGreaterMax() {
    // given
    ArraySpecification specification =
        new StatisticSpecification(
            StatisticType.MAX, ComparisonType.GREATER, 2.0, statisticsService);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertTrue(actual);
  }

  @Test
  void statisticSpecificationRejectsLessMin() {
    // given
    ArraySpecification specification =
        new StatisticSpecification(StatisticType.MIN, ComparisonType.LESS, 1.0, statisticsService);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertFalse(actual);
  }

  @Test
  void statisticSpecificationAcceptsEqualAverage() {
    // given
    ArraySpecification specification =
        new StatisticSpecification(
            StatisticType.AVERAGE, ComparisonType.EQUAL, 2.0, statisticsService);
    // when
    boolean actual = specification.isSatisfiedBy(ENTITY);
    // then
    assertTrue(actual);
  }
}
