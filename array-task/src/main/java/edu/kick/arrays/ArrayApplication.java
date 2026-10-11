package edu.kick.arrays;

import edu.kick.arrays.comparator.FirstElementComparator;
import edu.kick.arrays.comparator.SizeComparator;
import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.exception.ArrayProcessingException;
import edu.kick.arrays.factory.ArrayEntityFactory;
import edu.kick.arrays.factory.IntegerArrayEntityFactory;
import edu.kick.arrays.parser.RegexLineParser;
import edu.kick.arrays.reader.TextFileLineSource;
import edu.kick.arrays.repository.ArrayRepository;
import edu.kick.arrays.repository.InMemoryArrayRepository;
import edu.kick.arrays.service.ArrayLoadingService;
import edu.kick.arrays.service.ArraySortService;
import edu.kick.arrays.service.ArrayStatisticsService;
import edu.kick.arrays.service.impl.BasicArrayStatisticsService;
import edu.kick.arrays.service.impl.BubbleSortService;
import edu.kick.arrays.service.impl.FileArrayLoadingService;
import edu.kick.arrays.service.impl.InsertionSortService;
import edu.kick.arrays.specification.ArraySpecification;
import edu.kick.arrays.specification.ComparisonType;
import edu.kick.arrays.specification.SizeSpecification;
import edu.kick.arrays.specification.StatisticSpecification;
import edu.kick.arrays.specification.StatisticType;
import edu.kick.arrays.validator.DigitsLineValidator;
import edu.kick.arrays.warehouse.Warehouse;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayApplication {

  private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);
  private static final String DATA_FILE_PATH = "data/arrays.txt";
  private static final double SUM_THRESHOLD = 10.0;
  private static final int FIRST_ELEMENT_INDEX = 0;
  private static final int NEW_FIRST_ELEMENT = 100;

  private final ArrayLoadingService loadingService;
  private final ArrayStatisticsService statisticsService;
  private final ArraySortService bubbleSortService;
  private final ArraySortService insertionSortService;
  private final ArrayRepository repository;
  private final Warehouse warehouse;

  public ArrayApplication() {
    ArrayEntityFactory factory = new IntegerArrayEntityFactory();
    this.loadingService =
        new FileArrayLoadingService(
            new TextFileLineSource(), new DigitsLineValidator(), new RegexLineParser(), factory);
    this.statisticsService = new BasicArrayStatisticsService();
    this.bubbleSortService = new BubbleSortService(factory);
    this.insertionSortService = new InsertionSortService(factory);
    this.repository = InMemoryArrayRepository.getInstance();
    this.warehouse = Warehouse.getInstance();
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
        repository.add(entity);
      }
      demonstrateRepository();
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

  private void demonstrateRepository() {
    List<IntegerArrayEntity> all = repository.findAll();
    for (IntegerArrayEntity entity : all) {
      long id = entity.getId();
      LOGGER.info("Warehouse {}: {}", entity.getName(), warehouse.find(id));
    }
    ArraySpecification bigSum =
        new StatisticSpecification(
            StatisticType.SUM, ComparisonType.GREATER, SUM_THRESHOLD, statisticsService);
    LOGGER.info("Arrays with sum > {}: {}", SUM_THRESHOLD, repository.query(bigSum));
    LOGGER.info("Sorted by size: {}", repository.sort(new SizeComparator()));
    LOGGER.info("Sorted by first element: {}", repository.sort(new FirstElementComparator()));
    changeAndRemoveFirstNonEmpty();
  }

  private void changeAndRemoveFirstNonEmpty() {
    ArraySpecification nonEmpty = new SizeSpecification(ComparisonType.GREATER, 0);
    List<IntegerArrayEntity> found = repository.query(nonEmpty);
    if (!found.isEmpty()) {
      IntegerArrayEntity target = found.get(0);
      long id = target.getId();
      target.setValue(FIRST_ELEMENT_INDEX, NEW_FIRST_ELEMENT);
      LOGGER.info("After change {}: {}", target, warehouse.find(id));
      repository.remove(target);
      LOGGER.info("After removal of id {}: {}", id, warehouse.find(id));
    }
  }
}
