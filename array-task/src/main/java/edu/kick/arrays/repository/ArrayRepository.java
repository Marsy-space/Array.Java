package edu.kick.arrays.repository;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.specification.ArraySpecification;
import java.util.Comparator;
import java.util.List;

public interface ArrayRepository {

  boolean add(IntegerArrayEntity entity);

  boolean remove(IntegerArrayEntity entity);

  void clear();

  List<IntegerArrayEntity> findAll();

  List<IntegerArrayEntity> query(ArraySpecification specification);

  List<IntegerArrayEntity> sort(Comparator<IntegerArrayEntity> comparator);
}
