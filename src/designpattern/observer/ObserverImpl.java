package designpattern.observer;

public class ObserverImpl implements Observer {

    final String observerId;
    String linkedSubjId;

    public ObserverImpl(String observerId) {
        this.observerId = observerId;
    }

    @Override
    public void addToSubj(Subject subject) {
        linkedSubjId = subject.getState();
    }

    @Override
    public void update() {
        System.out.println(observerId + " received new message from subject " + linkedSubjId);
    }

    @Override
    public String getObserverId() {
        return this.observerId;
    }
}
