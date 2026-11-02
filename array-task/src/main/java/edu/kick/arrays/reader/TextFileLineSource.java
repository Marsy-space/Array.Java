package edu.kick.arrays.reader;

import edu.kick.arrays.exception.ArrayProcessingException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TextFileLineSource implements LineSource {

  @Override
  public List<String> readLines(String relativePath) throws ArrayProcessingException {
    Path path = Paths.get(relativePath);
    try {
      return Files.readAllLines(path, StandardCharsets.UTF_8);
    } catch (IOException exception) {
      throw new ArrayProcessingException("Cannot read file: " + relativePath, exception);
    }
  }
}
