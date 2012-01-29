package swing.machine.event;

import swing.machine.pattern.observer.interfaces.Observer;
import swing.machine.pattern.observer.interfaces.Subject;
import swing.machine.pattern.observer.DefaultSubject;


public class OutputEventStream<O> implements Subject<O> {

    protected DefaultSubject<O> subject = new DefaultSubject<O>();

    public void addObserver(Observer<O> o) {
        subject.addObserver(o);
    }

    public void notifyObservers(O v) {
        subject.notifyObservers(v);
    }

    public void send(O v) {
        notifyObservers(v);
    }
}
