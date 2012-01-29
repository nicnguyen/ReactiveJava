package swing.machine.event;

public interface EventStreamFilter<X,Y> {

    Y filter(X x);
    boolean accept(X x);
}
