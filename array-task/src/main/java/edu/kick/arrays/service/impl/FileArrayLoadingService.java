package edu.kick.arrays.service.impl;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.exception.ArrayProcessingException;
import edu.kick.arrays.factory.ArrayEntityFactory;
import edu.kick.arrays.parser.LineParser;
import edu.kick.arrays.reader.LineSource;
import edu.kick.arrays.service.ArrayLoadingService;
import edu.kick.arrays.validator.LineValidator;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileArrayLoadingService implements ArrayLoadingService {

  private static final Logger LOGGER = LogManager.getLogger(FileArrayLoadingService.class);

  private final LineSource lineSource;
  private final LineValidator validator;
  private final LineParser parser;
  private final ArrayEntityFactory factory;

  public FileArrayLoadingService(
      LineSource lineSource,
      LineValidator validator,
      LineParser parser,
      ArrayEntityFactory factory) {
    this.lineSource = lineSource;
    this.validator = validator;
    this.parser = parser;
    this.factory = factory;
  }

  @Override
  public List<IntegerArrayEntity> load(String filePath) throws ArrayProcessingException {
    List<String> lines = lineSource.readLines(filePath);
    List<IntegerArrayEntity> entities = new ArrayList<>();
    for (String line : lines) {
      boolean isValid = validator.isValid(line);
      if (isValid) {
        int[] values = parser.parse(line);
        IntegerArrayEntity entity = factory.create(values);
        entities.add(entity);
      } else {
        LOGGER.warn("Invalid line skipped: '{}'", line);
      }
    }
    return entities;
  }
}
