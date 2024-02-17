package designpattern.observer;

public class ObserverPattern {

    public static void main(String[] args) {
        Subject subject1 = new SubjectImpl("Sub 1");
        Subject subject2 = new SubjectImpl("Sub 2");
        Observer obs1 = new ObserverImpl("obs 1");
        Observer obs2 = new ObserverImpl("obs 2");

        subject1.add(obs1);
        subject2.add(obs2);

        obs1.addToSubj(subject1);
        obs2.addToSubj(subject2);

        subject1.notifyObservers();
        subject2.notifyObservers();
    }
}
