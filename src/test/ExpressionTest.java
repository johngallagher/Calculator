package test;

import main.Expression;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionTest {
    @Test
    public void twoNumbersWithAdd() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("3", "5", "+"));
        Expression expression = new Expression();
        expression.Type("3");
        expression.Type("+");
        expression.Type("5");
        Assert.assertEquals(expected, expression.output());
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "5", "*", "+"));
        Expression expression = new Expression();
        expression.Type("1");
        expression.Type("+");
        expression.Type("1");
        expression.Type("*");
        expression.Type("5");
        Assert.assertEquals(expected, expression.output());
    }

    @Test
    public void testWithBrackets() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "+", "5", "*"));
        Expression expression = new Expression();
        expression.Type("(");
        expression.Type("1");
        expression.Type("+");
        expression.Type("1");
        expression.Type(")");
        expression.Type("*");
        expression.Type("5");
        Assert.assertEquals(expected, expression.output());
    }
}
