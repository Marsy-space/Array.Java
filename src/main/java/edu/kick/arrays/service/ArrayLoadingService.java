package edu.kick.arrays.service;

import edu.kick.arrays.entity.IntegerArrayEntity;
import edu.kick.arrays.exception.ArrayProcessingException;
import java.util.List;

public interface ArrayLoadingService {

  List<IntegerArrayEntity> load(String filePath) throws ArrayProcessingException;
}
