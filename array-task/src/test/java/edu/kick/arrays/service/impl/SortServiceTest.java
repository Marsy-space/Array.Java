package edu.kick.arrays.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.factory.IntegerArrayEntityFactory;
import edu.kick.arrays.service.ArraySortService;
import org.junit.jupiter.api.Test;

class SortServiceTest {

  private static final IntegerArrayEntity UNSORTED =
      new IntegerArrayEntity(1L, "unsorted", new int[] {5, 2, 9, 1, 5});
  private static final int[] SORTED_VALUES = {1, 2, 5, 5, 9};

  private final ArraySortService bubbleSortService =
      new BubbleSortService(new IntegerArrayEntityFactory());
  private final ArraySortService insertionSortService =
      new InsertionSortService(new IntegerArrayEntityFactory());

  @Test
  void bubbleSortOrdersElementsAscending() {
    // given
    int[] expected = SORTED_VALUES;
    // when
    IntegerArrayEntity sorted = bubbleSortService.sort(UNSORTED);
    // then
    int[] actual = sorted.getValues();
    assertArrayEquals(expected, actual);
  }

  @Test
  void insertionSortOrdersElementsAscending() {
    // given
    int[] expected = SORTED_VALUES;
    // when
    IntegerArrayEntity sorted = insertionSortService.sort(UNSORTED);
    // then
    int[] actual = sorted.getValues();
    assertArrayEquals(expected, actual);
  }
}
