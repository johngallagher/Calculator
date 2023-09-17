package test;

import main.ReversePolishNotation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ReversePolishNotationTest {
    @Test
    public void testTwoNumbersAdded() {
        ArrayList<String> input = new ArrayList<>(Arrays.asList("3", "5", "+"));
        ReversePolishNotation n = new ReversePolishNotation();
        Assert.assertEquals("", "8.0", n.calculate(input));
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        ArrayList<String> input = new ArrayList<>(Arrays.asList("1", "1", "*", "5", "+"));
        ReversePolishNotation n = new ReversePolishNotation();
        Assert.assertEquals("", "6.0", n.calculate(input));
    }
    @Test
    public void testThreeNumbersWithAddAndMultiplyInDifferentOrder() {
        ArrayList<String> input = new ArrayList<>(Arrays.asList("1", "1", "+", "5", "*"));
        ReversePolishNotation n = new ReversePolishNotation();
        Assert.assertEquals("", "10.0", n.calculate(input));
    }
}
