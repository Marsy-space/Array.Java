package edu.kick.arrays.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexLineParser implements LineParser {

  private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");

  @Override
  public int[] parse(String line) {
    Matcher matcher = NUMBER_PATTERN.matcher(line);
    List<Integer> numbers = new ArrayList<>();
    while (matcher.find()) {
      String token = matcher.group();
      numbers.add(Integer.parseInt(token));
    }
    int[] values = new int[numbers.size()];
    for (int index = 0; index < values.length; index++) {
      values[index] = numbers.get(index);
    }
    return values;
  }
}
