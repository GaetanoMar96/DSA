package designpattern.observer;

import java.util.ArrayList;
import java.util.List;

public class SubjectImpl implements Subject {

    List<Observer> observers;
    String subjectId;

    public SubjectImpl(String subjectId) {
        this.subjectId = subjectId;
        observers = new ArrayList<>();
    }

    @Override
    public void add(Observer observer) {
        System.out.println("adding observer " + observer.getObserverId() + " to subject " + this.subjectId);
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::update);
    }

    @Override
    public String getState() {
        return this.subjectId;
    }
}
