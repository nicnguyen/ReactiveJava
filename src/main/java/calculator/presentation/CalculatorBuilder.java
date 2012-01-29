package calculator.presentation;

import calculator.machine.InputEnum;
import calculator.presentation.SpringUtilities;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: nic
 * Date: 03-Apr-2010
 * Time: 13:40:08
 * To change this template use File | Settings | File Templates.
 */
public class CalculatorBuilder {

    public Class getInputClientProperty()
    {
        return InputEnum.class;
    }

    public JFrame createGUI(int calculatorDigitDisplayWidth) {

        //Create and populate the panel.
        JPanel p = new JPanel(new SpringLayout());

        InputEnum[] items = new InputEnum[]{
                InputEnum.seven, InputEnum.eight, InputEnum.nine, InputEnum.divide,
                InputEnum.four, InputEnum.five, InputEnum.six, InputEnum.mult,
                InputEnum.one, InputEnum.two, InputEnum.three, InputEnum.minus,
                InputEnum.zero, InputEnum.point, InputEnum.equals, InputEnum.plus};

        for (InputEnum ie : items) {
            JButton b = new JButton(ie.toString());
            b.putClientProperty(getInputClientProperty(), ie);
            p.add(b);
        }

        //Lay out the panel.
        SpringUtilities.makeCompactGrid(p,
                4, 4, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad

        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));

        JTextField f = new JTextField(calculatorDigitDisplayWidth);
        f.setEditable(false);
        f.setHorizontalAlignment(JTextField.TRAILING);
        c.add(f);
        c.add(Box.createRigidArea(new Dimension(0, 5)));
        JButton b = new JButton(InputEnum.clear.toString());
        b.putClientProperty(InputEnum.class, InputEnum.clear);
        c.add(b);
        c.add(p);

        //Create and set up the window.
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        c.setOpaque(true);  //content panes must be opaque
        frame.setContentPane(c);

        return frame;

    }

    public void showGui(JFrame frame){
                //Display the window.
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}

