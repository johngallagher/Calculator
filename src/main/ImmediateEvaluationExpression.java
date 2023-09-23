package main;

import java.util.ArrayList;

public class ImmediateEvaluationExpression implements IExpression {
    private final ArrayList<String> expression;
    private Double firstNumber, result;
    private String operand;

    public ImmediateEvaluationExpression() {
        expression = new ArrayList<>();
        firstNumber = null;
        operand = null;
    }

    @Override
    public ArrayList<String> ToPostfix() {
        return null;
    }

    @Override
    public void Type(String s) {
    }

    @Override
    public void Reset() {
        this.result = 0.0;
        this.expression.clear();
        this.firstNumber = null;
        this.operand = null;
    }

    @Override
    public void TypeOperand(String operand, String currentDisplayText) {
        expression.add(currentDisplayText);
        expression.add(operand);
    }

    @Override
    public void TypeOpeningBracket() {

    }

    @Override
    public void TypeClosingBracket(String s) {

    }

    @Override
    public String Evaluate() {
        for (String s : expression) {
            if (s.matches("[0-9]+") || s.matches("[0-9]+\\.[0-9]+")) {
                if (firstNumber == null) {
                    firstNumber = Double.parseDouble(s);
                } else {
                    if (operand == "+") {
                        result = firstNumber + Double.parseDouble(s);
                    } else if (operand == "-") {
                        result = firstNumber - Double.parseDouble(s);
                    } else if (operand == "*") {
                        result = firstNumber * Double.parseDouble(s);
                    } else if (operand == "/") {
                        result = firstNumber / Double.parseDouble(s);
                    }
                    this.firstNumber = result;
                }
            } else {
                operand = s;
            }
        }

        return Double.toString(result);
    }

    @Override
    public void TypeEquals(String value) {
        expression.add(value);
    }

    @Override
    public void EnterNumber(String value) {

    }

    @Override
    public void EnterOperator(CalculatorOperation operator) {

    }

    @Override
    public void Clear() {

    }

    @Override
    public boolean LastCharacterIsClosedBracket() {
        return false;
    }

    @Override
    public boolean LastCharacterIsNotClosedBracket() {
        return false;
    }
}
