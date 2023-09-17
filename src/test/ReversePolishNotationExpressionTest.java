package test;

import main.ReversePolishNotationExpression;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ReversePolishNotationExpressionTest {
    @Test
    public void twoNumbersWithAdd() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("3", "5", "+"));
        ReversePolishNotationExpression expression = new ReversePolishNotationExpression();
        expression.Type("3");
        expression.Type("+");
        expression.Type("5");
        Assert.assertEquals(expected, expression.ToPostfix());
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "5", "*", "+"));
        ReversePolishNotationExpression expression = new ReversePolishNotationExpression();
        expression.Type("1");
        expression.Type("+");
        expression.Type("1");
        expression.Type("*");
        expression.Type("5");
        Assert.assertEquals(expected, expression.ToPostfix());
    }

    @Test
    public void testWithBrackets() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "+", "5", "*"));
        ReversePolishNotationExpression expression = new ReversePolishNotationExpression();
        expression.Type("(");
        expression.Type("1");
        expression.Type("+");
        expression.Type("1");
        expression.Type(")");
        expression.Type("*");
        expression.Type("5");
        Assert.assertEquals(expected, expression.ToPostfix());
    }
}
