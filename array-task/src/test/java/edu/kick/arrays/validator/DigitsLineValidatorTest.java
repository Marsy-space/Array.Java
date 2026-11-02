package edu.kick.arrays.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DigitsLineValidatorTest {

  private final LineValidator validator = new DigitsLineValidator();

  @ParameterizedTest
  @ValueSource(strings = {"1, 2, 3", "1 - 2 - 3", "3 4 7", "1; 2; 3", " "})
  void isValidAcceptsCorrectLines(String line) {
    // given
    String given = line;
    // when
    boolean actual = validator.isValid(given);
    // then
    assertTrue(actual);
  }

  @ParameterizedTest
  @ValueSource(strings = {"1y1 21 32", "1, 2, x3, 6..5, 77", "11- 2 – 42-"})
  void isValidRejectsIncorrectLines(String line) {
    // given
    String given = line;
    // when
    boolean actual = validator.isValid(given);
    // then
    assertFalse(actual);
  }

  @Test
  void isValidAcceptsEmptyString() {
    // given
    String given = "";
    // when
    boolean actual = validator.isValid(given);
    // then
    assertTrue(actual);
  }
}
