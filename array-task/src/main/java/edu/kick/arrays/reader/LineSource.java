package edu.kick.arrays.reader;

import edu.kick.arrays.exception.ArrayProcessingException;
import java.util.List;

public interface LineSource {

  List<String> readLines(String relativePath) throws ArrayProcessingException;
}
