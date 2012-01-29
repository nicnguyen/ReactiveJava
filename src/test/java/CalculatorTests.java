
import calculator.machine.CalculatorMachine;
import calculator.machine.InputEnum;
import junit.framework.Assert;
import org.junit.Test;

public class CalculatorTests {

    @Test
    public  void runCalculator() {
        CalculatorMachine c = new CalculatorMachine(32);
        String input = "12.3+3.5+1.1*2=";

        for (int i = 0; i < input.length(); ++i) {
            c.update(InputEnum.map(input.charAt(i)));
        }

        Assert.assertEquals("33.8", c.getValue().toString());
    }

}
