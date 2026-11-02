package edu.kick.arrays.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.kick.arrays.comparator.SizeComparator;
import edu.kick.arrays.entity.ArrayStatistics;
import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.impl.BasicArrayStatisticsService;
import edu.kick.arrays.specification.ArraySpecification;
import edu.kick.arrays.specification.ComparisonType;
import edu.kick.arrays.specification.StatisticSpecification;
import edu.kick.arrays.specification.StatisticType;
import edu.kick.arrays.warehouse.Warehouse;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryArrayRepositoryTest {

  private static final long FIRST_ID = 1L;
  private static final long SECOND_ID = 2L;
  private static final int[] FIRST_VALUES = {4, 1, 7};
  private static final int[] SECOND_VALUES = {10, 20};
  private static final Optional<ArrayStatistics> FIRST_STATISTICS =
      Optional.of(new ArrayStatistics(1, 7, 12L, 4.0, 3));
  private static final Optional<ArrayStatistics> CHANGED_STATISTICS =
      Optional.of(new ArrayStatistics(1, 100, 108L, 36.0, 3));

  private final ArrayRepository repository = InMemoryArrayRepository.getInstance();
  private final Warehouse warehouse = Warehouse.getInstance();
  private final IntegerArrayEntity first = new IntegerArrayEntity(FIRST_ID, "first", FIRST_VALUES);
  private final IntegerArrayEntity second =
      new IntegerArrayEntity(SECOND_ID, "second", SECOND_VALUES);

  @BeforeEach
  void clearRepository() {
    repository.clear();
  }

  @Test
  void addPutsStatisticsIntoWarehouse() {
    // given
    Optional<ArrayStatistics> expected = FIRST_STATISTICS;
    // when
    repository.add(first);
    // then
    Optional<ArrayStatistics> actual = warehouse.find(FIRST_ID);
    assertEquals(expected, actual);
  }

  @Test
  void changingElementRecalculatesStatisticsInWarehouse() {
    // given
    repository.add(first);
    Optional<ArrayStatistics> expected = CHANGED_STATISTICS;
    // when
    first.setValue(0, 100);
    // then
    Optional<ArrayStatistics> actual = warehouse.find(FIRST_ID);
    assertEquals(expected, actual);
  }

  @Test
  void removeDeletesStatisticsFromWarehouse() {
    // given
    repository.add(first);
    Optional<ArrayStatistics> expected = Optional.empty();
    // when
    repository.remove(first);
    // then
    Optional<ArrayStatistics> actual = warehouse.find(FIRST_ID);
    assertEquals(expected, actual);
  }

  @Test
  void queryReturnsEntitiesMatchingSpecification() {
    // given
    repository.add(first);
    repository.add(second);
    ArraySpecification specification =
        new StatisticSpecification(
            StatisticType.SUM, ComparisonType.GREATER, 20.0, new BasicArrayStatisticsService());
    List<IntegerArrayEntity> expected = List.of(second);
    // when
    List<IntegerArrayEntity> actual = repository.query(specification);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void sortOrdersEntitiesByGivenComparator() {
    // given
    repository.add(first);
    repository.add(second);
    List<IntegerArrayEntity> expected = List.of(second, first);
    // when
    List<IntegerArrayEntity> actual = repository.sort(new SizeComparator());
    // then
    assertEquals(expected, actual);
  }
}
