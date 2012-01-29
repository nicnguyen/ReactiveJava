package swing.machine.event;

import swing.machine.pattern.observer.DefaultSubject;
import swing.machine.pattern.observer.interfaces.Observer;
import swing.machine.pattern.observer.interfaces.Subject;

public class InputEventStream<I> implements Subject<I> {

    protected DefaultSubject<I> inputSubject = new DefaultSubject<I>();

    public void addObserver(Observer<I> o) {
        inputSubject.addObserver(o);
    }

    public void notifyObservers(I v) {
        inputSubject.notifyObservers(v);
    }

    public <O> OutputEventStream<O> bind(final EventStreamFilter<O,I> eventFilter) {
        final OutputEventStream<O> o = new OutputEventStream<O>();
        
        o.addObserver(new Observer<O>(){
            public void update(O v) {
                if (eventFilter.accept(v))
                    InputEventStream.this.notifyObservers(eventFilter.filter(v));
            }
        });

        return o;
    }
}

