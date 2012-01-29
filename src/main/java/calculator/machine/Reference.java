package calculator.machine;

import swing.machine.pattern.observer.interfaces.Observer;
import swing.machine.pattern.observer.interfaces.Subject;
import swing.machine.pattern.observer.DefaultSubject;


public class Reference<T> implements Subject<T> {
    public Reference(T i) {
        value = i;
    }

    public Reference() {

    }

    T value;


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        notifyObservers(value);

    }

    protected DefaultSubject<T> subject = new DefaultSubject<T>();

    public void addObserver(Observer<T> o) {
        subject.addObserver(o);
    }

    public void notifyObservers(T v) {
        subject.notifyObservers(v);
    }


}
