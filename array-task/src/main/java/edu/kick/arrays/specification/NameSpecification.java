package edu.kick.arrays.specification;

import edu.kick.arrays.entity.IntegerArrayEntity;

public class NameSpecification implements ArraySpecification {

  private final String name;

  public NameSpecification(String name) {
    this.name = name;
  }

  @Override
  public boolean isSatisfiedBy(IntegerArrayEntity entity) {
    String entityName = entity.getName();
    return name.equals(entityName);
  }
}
