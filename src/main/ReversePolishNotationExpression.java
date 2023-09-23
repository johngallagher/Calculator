package main;

import java.util.ArrayList;
import java.util.Stack;
import java.lang.Character;

public class ReversePolishNotationExpression implements IExpression {

    private final ArrayList<String> characters;
    private ArrayList<String> output;
    private Stack<String> stack;

    // Method is used to get the precedence of operators
    private boolean letterOrDigit(String s) {
        // boolean check
        if (Character.isLetterOrDigit(s.charAt(0)))
            return true;
        else
            return false;
    }

    // Operator having higher precedence
    // value will be returned
    int getPrecedence(String ch) {

        if (ch == "+" || ch == "-")
            return 1;
        else if (ch == "*" || ch == "/")
            return 2;
        else if (ch == "^")
            return 3;
        else
            return -1;
    }

    // Operator has Left --> Right associativity
    boolean hasLeftAssociativity(String ch) {
        if (ch == "+" || ch == "-" || ch == "/" || ch == "*") {
            return true;
        } else {
            return false;
        }
    }

    public ReversePolishNotationExpression() {
        output = new ArrayList<>();
        stack = new Stack<>();
        characters = new ArrayList<>();
    }

    // Method converts given infixto postfix expression
    // to illustrate shunting yard algorithm
    @Override
    public ArrayList<String> ToPostfix() {
        // pop all the remaining operators from
        // the stack and append them to output
        while (!stack.isEmpty()) {
            if (stack.peek() == "(")
                throw new IllegalArgumentException("Unmatched parenthesis");
            output.add(stack.pop());
        }
        return output;
    }


    public boolean LastCharacterIsClosedBracket() {
        if (characters.isEmpty())
            return false;
        else if (characters.get(characters.size() - 1) == ")")
            return true;
        else
            return false;
    }

    @Override
    public boolean LastCharacterIsNotClosedBracket() {
        return !LastCharacterIsClosedBracket();
    }

    @Override
    public boolean LastCharacterIsEquals() {
        if (characters.isEmpty())
            return false;
        else if (characters.get(characters.size() - 1) == "=")
            return true;
        else
            return false;
    }

    @Override
    public boolean LastCharacterIsNotEquals() {
        return !LastCharacterIsEquals();
    }

    @Override
    public void Type(String s) {
        characters.add(s);
        if (letterOrDigit(s))
            output.add(s);

            // If the scanned Token is an '('
            // push it to the stack
        else if (s == "(")
            stack.push(s);

            // If the scanned Token is an ')' pop and append
            // it to output from the stack until an '(' is
            // encountered
        else if (s == ")") {
            while (!stack.isEmpty()
                    && stack.peek() != "(")
                output.add(stack.pop());

            stack.pop();
        } else {
            while (
                    !stack.isEmpty()
                            && getPrecedence(s)
                            <= getPrecedence(stack.peek())
                            && hasLeftAssociativity(s)) {
                output.add(stack.pop());
            }
            stack.push(s);
        }
    }

    @Override
    public void Reset() {
        output.clear();
        stack.clear();
    }

    private boolean LastCharacterIsOperand() {
        if (output.isEmpty())
            return false;

        return IsOperand(output.get(output.size() - 1));
    }

    private boolean IsOperand(String s) {
        return s == "+" || s == "-" || s == "*" || s == "/" || s == "^";
    }

    private boolean IsNumber(String s) {
        return s.matches("[0-9]+") || s.matches("[0-9]+\\.[0-9]+");
    }

    private boolean LastCharacterWasNumber() {
        return !LastCharacterIsClosedBracket() && !LastCharacterIsOperand();
    }

    @Override
    public void TypeOperand(String operand, String value) {
        if (LastCharacterWasNumber()) {
            Type(value);
        }

        Type(operand);
    }

    @Override
    public void TypeOpeningBracket() {
        Type("(");
    }

    @Override
    public void TypeClosingBracket(String s) {
        Type(s);
        Type(")");
    }

    @Override
    public String Evaluate() {
        ArrayList<String> calculationInPolishNotation = ToPostfix();
        ReversePolishNotation reversePolishIndependent = new ReversePolishNotation();
        String calculationResult = reversePolishIndependent.calculate(calculationInPolishNotation);
        Reset();
        return calculationResult;
    }

    @Override
    public void TypeEquals(String value) {
        if (IsNumber(value)) {
            Type(value);
        }
    }

    @Override
    public void EnterNumber(String value) {
        Type(value);
    }

    @Override
    public void EnterOperator(CalculatorOperation operator) {
        Type(operator.getSymbol());
    }

    @Override
    public void Clear() {
        Reset();
    }

}

