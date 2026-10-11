package edu.kick.arrays.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class RegexLineParserTest {

  private static final int[] EXPECTED_VALUES = {1, 2, 3};
  private static final int[] EXPECTED_EMPTY = {};

  private final LineParser parser = new RegexLineParser();

  @Test
  void parseSplitsByMixedDelimiters() {
    // given
    String line = "1 - 2; 3";
    // when
    int[] actual = parser.parse(line);
    // then
    assertArrayEquals(EXPECTED_VALUES, actual);
  }

  @Test
  void parseReturnsEmptyArrayForBlankLine() {
    // given
    String line = "  ";
    // when
    int[] actual = parser.parse(line);
    // then
    assertArrayEquals(EXPECTED_EMPTY, actual);
  }
}
