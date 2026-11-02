package edu.kick.arrays.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DigitsLineValidator implements LineValidator {

  private static final Pattern VALID_LINE_PATTERN =
      Pattern.compile("^\\s*(\\d{1,9}([\\s,;-]+\\d{1,9})*)?\\s*$");

  @Override
  public boolean isValid(String line) {
    Matcher matcher = VALID_LINE_PATTERN.matcher(line);
    return matcher.matches();
  }
}
