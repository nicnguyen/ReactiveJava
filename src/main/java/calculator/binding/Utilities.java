package calculator.binding;

import swing.machine.Machine;
import swing.machine.event.InputEventStream;
import swing.machine.event.OutputEventStream;
import swing.machine.pattern.observer.interfaces.Subject;
import swing.machine.pattern.observer.interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Stack;
import java.util.EventListener;
import java.util.Map;
import java.util.HashMap;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: nic
 * Date: 03-Apr-2010
 * Time: 13:44:40
 * To change this template use File | Settings | File Templates.
 */
public class Utilities {
    public static <I,O> void streamIn(final OutputEventStream<I> out, Component root, Class srcCls, final Class<I> inputStreamClass) {
        Stack<Component> stack = new Stack<Component>();

        stack.push(root);
        Map<Class, EventListener> class2listener = new HashMap<Class, EventListener>();
        while (stack.size() > 0) {
            Component u = stack.pop();
            if (srcCls.isAssignableFrom(u.getClass())) { // u subclass of srcCls
                if (srcCls == JButton.class) { // u subclass of JButton
                    JButton b = (JButton) u;
                    ActionListener al = (ActionListener) class2listener.get(JButton.class);
                    if (al == null)

                        al = new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                JButton c = (JButton) e.getSource();
                                final I i = (I) c.getClientProperty(inputStreamClass);
                                out.send(i);
                            }
                        };
                    class2listener.put(JButton.class, al);
                    b.addActionListener(al);
                }
            }
            if (u instanceof Container)
                for (Component c : ((Container) u).getComponents()) {
                    stack.push(c);
                }
        }
    }

    public static <X> void streamOut(final InputEventStream<X> in, final JTextField output) {

            in.addObserver(new Observer<X>() {
                public void update(X v) {
                    output.setText(String.valueOf(v));
                }
            });
    }

    public static <X> X query(Class<X> cls, Container root) {
        Stack<Component> stack = new Stack<Component>();
        stack.push(root);
        while (stack.size() > 0) {
            Component u = stack.pop();
            if (cls.isAssignableFrom(u.getClass()))
                return (X) u;

            if (u instanceof Container)
                for (Component c : ((Container) u).getComponents()) {
                    stack.push(c);
                }
        }
        return null;
    }
}