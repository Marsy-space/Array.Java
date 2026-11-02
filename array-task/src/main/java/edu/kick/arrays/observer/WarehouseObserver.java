package edu.kick.arrays.observer;

import edu.kick.arrays.entity.ArrayStatistics;
import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.service.ArrayStatisticsService;
import edu.kick.arrays.warehouse.Warehouse;
import java.util.Optional;

public class WarehouseObserver implements ArrayObserver {

  private final Warehouse warehouse;
  private final ArrayStatisticsService statisticsService;

  public WarehouseObserver(Warehouse warehouse, ArrayStatisticsService statisticsService) {
    this.warehouse = warehouse;
    this.statisticsService = statisticsService;
  }

  @Override
  public void update(IntegerArrayEntity entity) {
    Optional<ArrayStatistics> statistics = statisticsService.calculateStatistics(entity);
    long id = entity.getId();
    if (statistics.isPresent()) {
      ArrayStatistics value = statistics.get();
      warehouse.put(id, value);
    } else {
      warehouse.remove(id);
    }
  }
}
