package designpattern.observer;

public interface Observer {

    void update();

    void addToSubj(Subject subject);

    String getObserverId();
}
