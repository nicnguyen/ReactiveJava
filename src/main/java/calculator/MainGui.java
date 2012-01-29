package calculator;

import calculator.machine.CalculatorMachine;
import calculator.machine.InputEnum;
import calculator.presentation.CalculatorBuilder;
import calculator.binding.Utilities;

import javax.swing.*;
import java.math.BigDecimal;

import swing.machine.event.InputEventStream;
import swing.machine.event.OutputEventStream;
import swing.machine.event.EventStreamFilter;
import swing.machine.event.IdentityEventStreamFilter;

public class MainGui {
    private final static int calculatorDigitDisplayWidth = 15;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CalculatorBuilder builder = new CalculatorBuilder();
                JFrame frame = builder.createGUI(calculatorDigitDisplayWidth);
                CalculatorMachine machine = new CalculatorMachine(calculatorDigitDisplayWidth);

                Utilities.streamIn(machine.getInputEventStream().bind(new IdentityEventStreamFilter<InputEnum>()),
                        frame,
                        JButton.class,
                        builder.getInputClientProperty()
                );

                JTextField txt = Utilities.query(JTextField.class, frame);

                InputEventStream<BigDecimal> in = new InputEventStream<BigDecimal>();
                Utilities.streamOut(in, txt);

                OutputEventStream<BigDecimal> out = in.bind(new IdentityEventStreamFilter<BigDecimal>());
                machine.setOutputEventStream(out);

                machine.initialize();
                builder.showGui(frame);
            }
        });
    }
}