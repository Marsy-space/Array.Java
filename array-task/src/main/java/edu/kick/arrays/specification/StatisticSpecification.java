package edu.kick.arrays.specification;

import edu.kick.arrays.entity.ArrayStatistics;
import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.ArrayStatisticsService;
import java.util.Optional;

public class StatisticSpecification implements ArraySpecification {

  private final StatisticType statisticType;
  private final ComparisonType comparisonType;
  private final double expected;
  private final ArrayStatisticsService statisticsService;

  public StatisticSpecification(
      StatisticType statisticType,
      ComparisonType comparisonType,
      double expected,
      ArrayStatisticsService statisticsService) {
    this.statisticType = statisticType;
    this.comparisonType = comparisonType;
    this.expected = expected;
    this.statisticsService = statisticsService;
  }

  @Override
  public boolean isSatisfiedBy(IntegerArrayEntity entity) {
    Optional<ArrayStatistics> statistics = statisticsService.calculateStatistics(entity);
    if (statistics.isPresent()) {
      ArrayStatistics value = statistics.get();
      double actual = selectValue(value);
      return comparisonType.matches(actual, expected);
    }
    return false;
  }

  private double selectValue(ArrayStatistics statistics) {
    return switch (statisticType) {
      case SUM -> (double) statistics.getSum();
      case AVERAGE -> statistics.getAverage();
      case MAX -> (double) statistics.getMax();
      case MIN -> (double) statistics.getMin();
    };
  }
}
