package test;

import main.IExpression;
import main.ImmediateEvaluationExpression;
import main.ReversePolishNotationExpression;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmediateEvaluationExpressionTest {
    @Test
    public void twoNumbersWithAdd() {
        IExpression expression = new ImmediateEvaluationExpression();
        expression.TypeOperand("+", "3");
        expression.TypeEquals("5");
        Assert.assertEquals("8.0", expression.Evaluate());
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        IExpression expression = new ImmediateEvaluationExpression();
        expression.TypeOperand("+", "1");
        expression.TypeOperand("*", "1");
        expression.TypeEquals("5");
        Assert.assertEquals("10.0", expression.Evaluate());
    }
}