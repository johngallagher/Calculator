package test;

import main.IExpression;
import main.ReversePolishNotationExpression;
import org.junit.Assert;
import org.junit.Test;

public class ReversePolishNotationExpressionTest {
    @Test
    public void twoNumbersWithAdd() {
        IExpression expression = new ReversePolishNotationExpression();
        expression.TypeOperand("+", "3");
        expression.TypeEquals("5");
        Assert.assertEquals("8.0", expression.Evaluate());
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        IExpression expression = new ReversePolishNotationExpression();
        expression.TypeOperand("+", "1");
        expression.TypeOperand("*", "1");
        expression.TypeEquals("5");
        Assert.assertEquals("6.0", expression.Evaluate());
    }

    @Test
    public void testWithBrackets() {
        IExpression expression = new ReversePolishNotationExpression();
        expression.TypeOpeningBracket();
        expression.TypeOperand("+", "1");
        expression.TypeClosingBracket("1");
        expression.TypeOperand("*", "1");
        expression.TypeEquals("5");
        Assert.assertEquals("10.0", expression.Evaluate());
    }
}
