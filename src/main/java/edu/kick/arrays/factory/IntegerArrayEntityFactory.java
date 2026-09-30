package edu.kick.arrays.factory;

import edu.kick.arrays.entity.IntegerArrayEntity;

public class IntegerArrayEntityFactory implements ArrayEntityFactory {

  @Override
  public IntegerArrayEntity create(int[] values) {
    return new IntegerArrayEntity(values);
  }
}
