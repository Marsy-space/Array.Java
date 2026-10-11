package edu.kick.arrays.factory;

import edu.kick.arrays.entity.IntegerArrayEntity;

public interface ArrayEntityFactory {

  IntegerArrayEntity create(int[] values);

  IntegerArrayEntity create(String name, int[] values);
}
