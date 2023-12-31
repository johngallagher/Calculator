package main;

import java.util.ArrayList;

public interface IExpression {
    // Method converts given infixto postfix expression
    // to illustrate shunting yard algorithm
    ArrayList<String> ToPostfix();

    void Type(String s);

    void Reset();

    void TypeOperand(String operand, String value);

    void TypeOpeningBracket();

    void TypeClosingBracket(String s);

    String Evaluate();

    void TypeEquals(String value);

    void EnterNumber(String value);

    void EnterOperation(CalculatorOperation operator);

    void Clear();

    boolean LastTermWasClosedBracket();

    boolean LastTermWasNotClosedBracket();

    boolean LastCharacterWasEquals();

    boolean LastCharacterIsNotEquals();

    default void EnterNumberAndOperation(String number, CalculatorOperation operation) {
        if (LastTermWasNotClosedBracket())
            EnterNumber(number);
        EnterOperation(operation);
    }
}
