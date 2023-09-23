package main;

import java.util.ArrayList;
import java.util.Stack;

public class ReversePolishNotation {

    public String calculate(ArrayList<String> input) {
        Stack<Double> stack = new Stack<>();
        for (String s : input) {
            if (s.matches("[0-9]+")) {
                stack.push(Double.parseDouble(s));
            } else if (s.matches("[0-9]+\\.[0-9]+")) {
                stack.push(Double.parseDouble(s));
            } else if (s.matches("[+\\-*/^]")) {
                Double first = stack.pop();
                Double second = stack.pop();
                if (s.equals("+")) {
                    stack.push(first + second);
                } else if (s.equals("-")) {
                    stack.push(second - first);
                } else if (s.equals("*")) {
                    stack.push(first * second);
                } else if (s.equals("/")) {
                    stack.push(second / first);
                } else if (s.equals("^")) {
                    stack.push(Math.pow(second, first));
                }
            } else if (s == "=") {
                // Do nothing
            } else {
                throw new IllegalArgumentException("Invalid input " + s + " in expression");
            }
        }

        return stack.pop().toString();
    }
}
