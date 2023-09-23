package main;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalculatorButton {
    private final CalculatorOperation operation;
    private final String buttonText;

    public CalculatorButton(CalculatorOperation operation, String buttonText) {
        this.operation = operation;
        this.buttonText = buttonText;
    }

    public static CalculatorButton fromActionEvent(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        String buttonText = button.getText();
        switch (buttonText) {
            case "+":
                return new CalculatorButton(CalculatorOperation.Add, buttonText);
            case "-":
                return new CalculatorButton(CalculatorOperation.Subtract, buttonText);
            case "*":
                return new CalculatorButton(CalculatorOperation.Multiply, buttonText);
            case "/":
                return new CalculatorButton(CalculatorOperation.Divide, buttonText);
            case "=":
                return new CalculatorButton(CalculatorOperation.Equals, buttonText);
            case "CLR":
                return new CalculatorButton(CalculatorOperation.Clear, buttonText);
            case "CE":
                return new CalculatorButton(CalculatorOperation.Delete, buttonText);
            case ".":
                return new CalculatorButton(CalculatorOperation.Decimal, buttonText);
            case "(":
                return new CalculatorButton(CalculatorOperation.OpenBracket, buttonText);
            case ")":
                return new CalculatorButton(CalculatorOperation.CloseBracket, buttonText);
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return new CalculatorButton(CalculatorOperation.Number, buttonText);
            default:
                return new CalculatorButton(CalculatorOperation.None, buttonText);
        }
    }

    public boolean WasOperation() {
        return this.operation == CalculatorOperation.Add || this.operation == CalculatorOperation.Subtract || this.operation == CalculatorOperation.Multiply || this.operation == CalculatorOperation.Divide;
    }

    public CalculatorOperation Operation() {
        return this.operation;
    }

    public boolean WasANumber() {
        return this.operation == CalculatorOperation.Number;
    }

    public String Number() {
        return this.buttonText;
    }

    public boolean WasDecimalPoint() {
        return this.operation == CalculatorOperation.Decimal;
    }

    public boolean WasClear() {
        return this.operation == CalculatorOperation.Clear;
    }

    public boolean WasDelete() {
        return this.operation == CalculatorOperation.Delete;
    }

    public boolean WasEquals() {
        return this.operation == CalculatorOperation.Equals;
    }

    public boolean WasClosedBracket() {
        return this.operation == CalculatorOperation.CloseBracket;
    }

    public boolean WasOpenBracket() {
        return this.operation == CalculatorOperation.OpenBracket;
    }

    public CalculatorOperation Type() {
        return this.operation;
    }
}
