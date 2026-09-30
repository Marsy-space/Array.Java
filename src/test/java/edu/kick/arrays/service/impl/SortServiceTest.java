package edu.kick.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.factory.IntegerArrayEntityFactory;
import edu.kick.arrays.service.ArraySortService;
import org.junit.jupiter.api.Test;

class SortServiceTest {

  private static final IntegerArrayEntity UNSORTED =
      new IntegerArrayEntity(new int[] {5, 2, 9, 1, 5});
  private static final IntegerArrayEntity SORTED =
      new IntegerArrayEntity(new int[] {1, 2, 5, 5, 9});

  private final ArraySortService bubbleSortService =
      new BubbleSortService(new IntegerArrayEntityFactory());
  private final ArraySortService insertionSortService =
      new InsertionSortService(new IntegerArrayEntityFactory());

  @Test
  void bubbleSortOrdersElementsAscending() {
    // given
    IntegerArrayEntity expected = SORTED;
    // when
    IntegerArrayEntity actual = bubbleSortService.sort(UNSORTED);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void insertionSortOrdersElementsAscending() {
    // given
    IntegerArrayEntity expected = SORTED;
    // when
    IntegerArrayEntity actual = insertionSortService.sort(UNSORTED);
    // then
    assertEquals(expected, actual);
  }
}
