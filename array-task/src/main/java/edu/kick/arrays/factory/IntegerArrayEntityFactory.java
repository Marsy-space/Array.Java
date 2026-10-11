package edu.kick.arrays.factory;

import edu.kick.arrays.entity.IntegerArrayEntity;

public class IntegerArrayEntityFactory implements ArrayEntityFactory {

  private static final String DEFAULT_NAME_PREFIX = "array-";

  private long nextId = 1;

  @Override
  public IntegerArrayEntity create(int[] values) {
    String name = DEFAULT_NAME_PREFIX + nextId;
    return create(name, values);
  }

  @Override
  public IntegerArrayEntity create(String name, int[] values) {
    long id = nextId;
    nextId++;
    return new IntegerArrayEntity(id, name, values);
  }
}
