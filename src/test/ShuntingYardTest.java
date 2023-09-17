package test;

import main.ShuntingYard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ShuntingYardTest {
    @Test
    public void twoNumbersWithAdd() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("3", "5", "+"));
        ShuntingYard shuntingYard = new ShuntingYard();
        shuntingYard.Type("3");
        shuntingYard.Type("+");
        shuntingYard.Type("5");
        Assert.assertEquals(expected, shuntingYard.output());
    }

    @Test
    public void testThreeNumbersWithAddAndMultiply() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "5", "*", "+"));
        ShuntingYard shuntingYard = new ShuntingYard();
        shuntingYard.Type("1");
        shuntingYard.Type("+");
        shuntingYard.Type("1");
        shuntingYard.Type("*");
        shuntingYard.Type("5");
        Assert.assertEquals(expected, shuntingYard.output());
    }

    @Test
    public void testWithBrackets() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "1", "+", "5", "*"));
        ShuntingYard shuntingYard = new ShuntingYard();
        shuntingYard.Type("(");
        shuntingYard.Type("1");
        shuntingYard.Type("+");
        shuntingYard.Type("1");
        shuntingYard.Type(")");
        shuntingYard.Type("*");
        shuntingYard.Type("5");
        Assert.assertEquals(expected, shuntingYard.output());
    }
}
