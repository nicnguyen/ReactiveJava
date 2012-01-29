package swing.machine.pattern.observer.interfaces;

public interface Subject<X> {

    void addObserver(Observer<X> o);
    void notifyObservers(X v);

}