package swing.machine;

import swing.machine.state.State;
import swing.machine.event.OutputEventStream;
import swing.machine.event.InputEventStream;


public abstract class Machine<I, O> {

    protected InputEventStream<I> inputEventStream;

    public void setOutputEventStream(OutputEventStream<O> outputEventStream) {
        this.outputEventStream = outputEventStream;
    }

    protected OutputEventStream<O> outputEventStream;

    private State<I> state;

    public void setState(State<I> h) {
        this.state = h;
    }

    public boolean update(I e) {
        return state.update(e);
    }

    public boolean accept(){
        return state.accept();
    }
    public InputEventStream<I> getInputEventStream() {
        return inputEventStream;
    }

    public OutputEventStream<O> getOutputEventStream() {
        return outputEventStream;
    }
}