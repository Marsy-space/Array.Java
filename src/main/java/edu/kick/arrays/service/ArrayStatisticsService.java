package edu.kick.arrays.service;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.Optional;

public interface ArrayStatisticsService {

  Optional<Integer> findMin(IntegerArrayEntity entity);

  Optional<Integer> findMax(IntegerArrayEntity entity);

  Optional<Long> calculateSum(IntegerArrayEntity entity);

  Optional<Double> calculateAverage(IntegerArrayEntity entity);
}
