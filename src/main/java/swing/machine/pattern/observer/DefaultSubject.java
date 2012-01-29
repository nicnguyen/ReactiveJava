package swing.machine.pattern.observer;

import swing.machine.pattern.observer.interfaces.Subject;
import swing.machine.pattern.observer.interfaces.Observer;

import java.util.List;
import java.util.LinkedList;

public class DefaultSubject<X> implements Subject<X> {
    List<Observer> observers = new LinkedList<Observer>();

    public void addObserver(Observer<X> o) {
        observers.add(o);
    }

    public void notifyObservers(X v) {
        for (Observer o : observers) {
            o.update(v);
        }
    }
}
