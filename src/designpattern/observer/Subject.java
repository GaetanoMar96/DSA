package designpattern.observer;

public interface Subject {

    void add(Observer observer);

    void remove(Observer observer);

    void notifyObservers();

    String getState();
}
