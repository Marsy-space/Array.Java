package edu.kick.arrays.specification;

import edu.kick.arrays.entity.IntegerArrayEntity;

public interface ArraySpecification {

  boolean isSatisfiedBy(IntegerArrayEntity entity);
}
