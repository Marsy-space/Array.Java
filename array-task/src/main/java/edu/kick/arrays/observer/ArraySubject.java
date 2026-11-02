package edu.kick.arrays.observer;

public interface ArraySubject {

  void attach(ArrayObserver observer);

  void detach(ArrayObserver observer);

  void notifyObservers();
}
