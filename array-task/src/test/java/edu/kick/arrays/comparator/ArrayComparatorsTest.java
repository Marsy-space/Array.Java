package edu.kick.arrays.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.kick.arrays.entity.IntegerArrayEntity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class ArrayComparatorsTest {

  private static final IntegerArrayEntity ALPHA =
      new IntegerArrayEntity(3L, "b", new int[] {5, 1});
  private static final IntegerArrayEntity BETA =
      new IntegerArrayEntity(1L, "c", new int[] {2, 9, 9});
  private static final IntegerArrayEntity GAMMA =
      new IntegerArrayEntity(2L, "a", new int[] {7});
  private static final List<IntegerArrayEntity> UNSORTED = List.of(ALPHA, BETA, GAMMA);

  @Test
  void idComparatorOrdersById() {
    // given
    List<IntegerArrayEntity> expected = List.of(BETA, GAMMA, ALPHA);
    List<IntegerArrayEntity> actual = new ArrayList<>(UNSORTED);
    Comparator<IntegerArrayEntity> comparator = new IdComparator();
    // when
    actual.sort(comparator);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void nameComparatorOrdersByName() {
    // given
    List<IntegerArrayEntity> expected = List.of(GAMMA, ALPHA, BETA);
    List<IntegerArrayEntity> actual = new ArrayList<>(UNSORTED);
    Comparator<IntegerArrayEntity> comparator = new NameComparator();
    // when
    actual.sort(comparator);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void firstElementComparatorOrdersByFirstElement() {
    // given
    List<IntegerArrayEntity> expected = List.of(BETA, ALPHA, GAMMA);
    List<IntegerArrayEntity> actual = new ArrayList<>(UNSORTED);
    Comparator<IntegerArrayEntity> comparator = new FirstElementComparator();
    // when
    actual.sort(comparator);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void sizeComparatorOrdersBySize() {
    // given
    List<IntegerArrayEntity> expected = List.of(GAMMA, ALPHA, BETA);
    List<IntegerArrayEntity> actual = new ArrayList<>(UNSORTED);
    Comparator<IntegerArrayEntity> comparator = new SizeComparator();
    // when
    actual.sort(comparator);
    // then
    assertEquals(expected, actual);
  }
}
