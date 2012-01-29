package swing.machine.state;

public class State<I> {

    public boolean update(I e) {
        return false;
    }

    protected boolean accept = false;

    public boolean accept(){
        return accept;
    }

}




