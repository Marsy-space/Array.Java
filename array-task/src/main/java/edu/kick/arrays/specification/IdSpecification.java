package edu.kick.arrays.specification;

import edu.kick.arrays.entity.IntegerArrayEntity;

public class IdSpecification implements ArraySpecification {

  private final long id;

  public IdSpecification(long id) {
    this.id = id;
  }

  @Override
  public boolean isSatisfiedBy(IntegerArrayEntity entity) {
    long entityId = entity.getId();
    return entityId == id;
  }
}
