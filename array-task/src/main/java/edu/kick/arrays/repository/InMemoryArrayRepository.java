package edu.kick.arrays.repository;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.observer.ArrayObserver;
import edu.kick.arrays.observer.WarehouseObserver;
import edu.kick.arrays.service.impl.BasicArrayStatisticsService;
import edu.kick.arrays.specification.ArraySpecification;
import edu.kick.arrays.warehouse.Warehouse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InMemoryArrayRepository implements ArrayRepository {

  private static InMemoryArrayRepository instance;

  private final List<IntegerArrayEntity> entities = new ArrayList<>();
  private final Warehouse warehouse;
  private final ArrayObserver observer;

  private InMemoryArrayRepository() {
    this.warehouse = Warehouse.getInstance();
    this.observer = new WarehouseObserver(warehouse, new BasicArrayStatisticsService());
  }

  public static InMemoryArrayRepository getInstance() {
    if (instance == null) {
      instance = new InMemoryArrayRepository();
    }
    return instance;
  }

  @Override
  public boolean add(IntegerArrayEntity entity) {
    boolean isNew = !entities.contains(entity);
    if (isNew) {
      entities.add(entity);
      entity.attach(observer);
      observer.update(entity);
    }
    return isNew;
  }

  @Override
  public boolean remove(IntegerArrayEntity entity) {
    boolean isRemoved = entities.remove(entity);
    if (isRemoved) {
      entity.detach(observer);
      long id = entity.getId();
      warehouse.remove(id);
    }
    return isRemoved;
  }

  @Override
  public void clear() {
    for (IntegerArrayEntity entity : entities) {
      entity.detach(observer);
      long id = entity.getId();
      warehouse.remove(id);
    }
    entities.clear();
  }

  @Override
  public List<IntegerArrayEntity> findAll() {
    return new ArrayList<>(entities);
  }

  @Override
  public List<IntegerArrayEntity> query(ArraySpecification specification) {
    List<IntegerArrayEntity> result = new ArrayList<>();
    for (IntegerArrayEntity entity : entities) {
      boolean isSatisfied = specification.isSatisfiedBy(entity);
      if (isSatisfied) {
        result.add(entity);
      }
    }
    return result;
  }

  @Override
  public List<IntegerArrayEntity> sort(Comparator<IntegerArrayEntity> comparator) {
    List<IntegerArrayEntity> sorted = new ArrayList<>(entities);
    sorted.sort(comparator);
    return sorted;
  }
}
