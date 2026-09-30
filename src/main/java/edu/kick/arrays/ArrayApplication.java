package edu.kick.arrays;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.exception.ArrayProcessingException;
import edu.kick.arrays.factory.ArrayEntityFactory;
import edu.kick.arrays.factory.IntegerArrayEntityFactory;
import edu.kick.arrays.parser.RegexLineParser;
import edu.kick.arrays.reader.TextFileLineSource;
import edu.kick.arrays.service.ArrayLoadingService;
import edu.kick.arrays.service.ArraySortService;
import edu.kick.arrays.service.ArrayStatisticsService;
import edu.kick.arrays.service.impl.BasicArrayStatisticsService;
import edu.kick.arrays.service.impl.BubbleSortService;
import edu.kick.arrays.service.impl.FileArrayLoadingService;
import edu.kick.arrays.service.impl.InsertionSortService;
import edu.kick.arrays.validator.DigitsLineValidator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayApplication {

  private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);
  private static final String DATA_FILE_PATH = "data/arrays.txt";

  private final ArrayLoadingService loadingService;
  private final ArrayStatisticsService statisticsService;
  private final ArraySortService bubbleSortService;
  private final ArraySortService insertionSortService;

  public ArrayApplication() {
    ArrayEntityFactory factory = new IntegerArrayEntityFactory();
    this.loadingService =
        new FileArrayLoadingService(
            new TextFileLineSource(), new DigitsLineValidator(), new RegexLineParser(), factory);
    this.statisticsService = new BasicArrayStatisticsService();
    this.bubbleSortService = new BubbleSortService(factory);
    this.insertionSortService = new InsertionSortService(factory);
  }

  public static void main(String[] args) {
    ArrayApplication application = new ArrayApplication();
    application.run();
  }

  private void run() {
    try {
      List<IntegerArrayEntity> entities = loadingService.load(DATA_FILE_PATH);
      for (IntegerArrayEntity entity : entities) {
        report(entity);
      }
    } catch (ArrayProcessingException exception) {
      LOGGER.error("Array processing failed", exception);
    }
  }

  private void report(IntegerArrayEntity entity) {
    LOGGER.info("Array: {}", entity);
    LOGGER.info("Min: {}", statisticsService.findMin(entity));
    LOGGER.info("Max: {}", statisticsService.findMax(entity));
    LOGGER.info("Sum: {}", statisticsService.calculateSum(entity));
    LOGGER.info("Average: {}", statisticsService.calculateAverage(entity));
    LOGGER.info("Bubble sort: {}", bubbleSortService.sort(entity));
    LOGGER.info("Insertion sort: {}", insertionSortService.sort(entity));
  }
}
