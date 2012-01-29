package swing.machine.event;

public class IdentityEventStreamFilter<X> implements EventStreamFilter<X,X> {

    public X filter(X x) {
        return x;
    }

    public boolean accept(X x) {
        return true;
    }
}
