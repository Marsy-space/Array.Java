package edu.kick.arrays.warehouse;

import edu.kick.arrays.entity.ArrayStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse {

  private static Warehouse instance;

  private final Map<Long, ArrayStatistics> statisticsById = new HashMap<>();

  private Warehouse() {
  }

  public static Warehouse getInstance() {
    if (instance == null) {
      instance = new Warehouse();
    }
    return instance;
  }

  public void put(long id, ArrayStatistics statistics) {
    statisticsById.put(id, statistics);
  }

  public void remove(long id) {
    statisticsById.remove(id);
  }

  public Optional<ArrayStatistics> find(long id) {
    ArrayStatistics statistics = statisticsById.get(id);
    return Optional.ofNullable(statistics);
  }
}
