package test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import main.Calculator;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddThreeAndTwo() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[3]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[2]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Failed to add 3 and 2", "5.0", calculator.valueText());
    }

    @Test
    public void testSubtractTwoFromThree() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[3]));
        calculator.actionPerformed(eventWithButton(calculator.Sub_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[2]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Failed to subtract 3 from 2", "1.0", calculator.valueText());
    }


    @Test
    public void testMultiplyThreeAndTwo() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[3]));
        calculator.actionPerformed(eventWithButton(calculator.Mul_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[2]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Failed to multiply 3 and 2", "6.0", calculator.valueText());
    }

    @Test
    public void testDivideThreeByTwo() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[3]));
        calculator.actionPerformed(eventWithButton(calculator.Div_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[2]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Failed to divide 3 by 2", "1.5", calculator.valueText());
    }

    @Test
    public void testThreeNumbersSameOperator() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[5]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Failed to add 1, 1 and 5", "7.0", calculator.valueText());
    }

    @Test
    public void testThreeNumbersWithMultiplyAndAdd() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Mul_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[5]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("1 + 1 * 5 = 6", "6.0", calculator.valueText());
    }
    @Ignore
    @Test
    public void testBracketsWork() {
        Calculator calculator = new Calculator();
//        calculator.actionPerformed(eventWithButton(calculator.openBracket_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
//        calculator.actionPerformed(eventWithButton(calculator.closeBracket_btn));
        calculator.actionPerformed(eventWithButton(calculator.Mul_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[5]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("(1 + 1) * 5 = 10", "10.0", calculator.valueText());
    }

    @Ignore
    @Test
    public void testPressingEqualsAfterACalculationClears() {
        Calculator calculator = new Calculator();
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.Add_btn));
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("1 + 1 = 2", "2.0", calculator.valueText());
        calculator.actionPerformed(eventWithButton(calculator.NumberBtn[1]));
        calculator.actionPerformed(eventWithButton(calculator.equalBtn));
        Assert.assertEquals("Appending a 1 at the end of the output", "2.01", calculator.valueText());
    }

    private static ActionEvent eventWithButton(JButton calculator) {
        return new ActionEvent(calculator, 0, "");
    }
}
