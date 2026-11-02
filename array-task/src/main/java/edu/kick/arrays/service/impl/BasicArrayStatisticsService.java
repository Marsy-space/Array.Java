package edu.kick.arrays.service.impl;

import edu.kick.arrays.entity.ArrayStatistics;
import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.ArrayStatisticsService;
import java.util.Optional;

public class BasicArrayStatisticsService implements ArrayStatisticsService {

  @Override
  public Optional<Integer> findMin(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    if (values.length > 0) {
      int min = values[0];
      for (int value : values) {
        if (value < min) {
          min = value;
        }
      }
      return Optional.of(min);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Integer> findMax(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    if (values.length > 0) {
      int max = values[0];
      for (int value : values) {
        if (value > max) {
          max = value;
        }
      }
      return Optional.of(max);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Long> calculateSum(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    if (values.length > 0) {
      long sum = 0;
      for (int value : values) {
        sum += value;
      }
      return Optional.of(sum);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Double> calculateAverage(IntegerArrayEntity entity) {
    int[] values = entity.getValues();
    if (values.length > 0) {
      long sum = 0;
      for (int value : values) {
        sum += value;
      }
      double average = (double) sum / values.length;
      return Optional.of(average);
    }
    return Optional.empty();
  }

  @Override
  public Optional<ArrayStatistics> calculateStatistics(IntegerArrayEntity entity) {
    Optional<Integer> min = findMin(entity);
    if (min.isPresent()) {
      Optional<Integer> max = findMax(entity);
      Optional<Long> sum = calculateSum(entity);
      Optional<Double> average = calculateAverage(entity);
      int size = entity.getSize();
      ArrayStatistics statistics =
          new ArrayStatistics(min.get(), max.get(), sum.get(), average.get(), size);
      return Optional.of(statistics);
    }
    return Optional.empty();
  }
}
