package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import static main.CalculatorOperation.Equals;

public class Calculator extends JFrame implements ActionListener {
    private JButton deleteButton, clearButton;
    public JButton NumberButtons[];
    public JButton functionBtn[];
    public JButton Add_btn, Sub_btn, Mul_btn, Div_btn, equalBtn, decBtn, openBracket_btn, closeBracket_btn;
    private JPanel keypad;
    private JTextField text;
    private int x = 0;
    private IExpression expression;
    private DisplayBuffer displayBuffer;

    public Calculator() {
        initialize();
    }

    private void initialize() {
        this.setSize(new Dimension(300, 420));
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        text = new JTextField();
        text.setSize(new Dimension(250, 40));
        text.setLocation(new Point(20, 20));
        text.setFont(new Font("Orbitron", Font.PLAIN, 20));
        text.setEditable(false);
        text.setForeground(Color.black);
        text.setHorizontalAlignment(JTextField.RIGHT);

        keypad = new JPanel();
        keypad.setLayout(new GridLayout(5, 4));
        keypad.setSize(new Dimension(250, 250));
        keypad.setLocation(new Point(20, 70));

        clearButton = new JButton("CE");
        clearButton.setLocation(new Point(20, 330));

        deleteButton = new JButton("CLR");
        deleteButton.setLocation(new Point(170, 330));

        // Start here
        // Objective? Add SIN
        // Use the four rules of simple design to refactor first
        List<List<String>> rows = Arrays.asList(
                Arrays.asList("7", "8", "9", "+"),
                Arrays.asList("6", "5", "4", "-"),
                Arrays.asList("3", "2", "1", "*"),
                Arrays.asList(".", "0", "=", "/"),
                Arrays.asList("(", ")", "SIN")
        );
        for (List<String> row : rows) {
            for (String cell : row) {
                keypad.add(ButtonFromCell(cell));
            }
        }

        this.add(clearButton);
        this.add(deleteButton);
        this.add(text);
        this.add(keypad);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        expression = new ReversePolishNotationExpression();
        displayBuffer = new DisplayBuffer(text);
    }

    private JButton ButtonFromCell(String cell) {
        if (cell.matches("\\d")) {
            return CreateNumberButton(Integer.valueOf(cell));
        } else {
            return CreateFunctionButton(cell);
        }
    }

    private JButton CreateFunctionButton(String text) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Orbitron", Font.PLAIN, 20));
        button.setSize(new Dimension(100, 30));
        button.addActionListener(this);
        button.setForeground(Color.black);
        return button;
    }

    private JButton CreateNumberButton(int i) {
        JButton numberButton = new JButton(String.valueOf(i));
        numberButton.setFocusable(false);
        numberButton.setFont(new Font("Orbitron", Font.PLAIN, 20));
        numberButton.setSize(new Dimension(10, 10));
        numberButton.setVerticalTextPosition(JButton.CENTER);
        numberButton.setHorizontalTextPosition(JButton.CENTER);
        numberButton.addActionListener(this);
        numberButton.setForeground(Color.black);
        return numberButton;
    }

    public String valueText() {
        return text.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CalculatorButton buttonPressed = CalculatorButton.fromActionEvent(e);
        switch (buttonPressed.Type()) {
            case Number -> {
                if (expression.LastCharacterWasEquals())
                    displayBuffer.Clear();
                displayBuffer.Append(buttonPressed.Number());
            }
            case Decimal -> displayBuffer.Append(".");
            case Clear -> {
                displayBuffer.Clear();
                expression.Clear();
            }
            case Delete -> displayBuffer.Clear();
            case OpenBracket -> expression.EnterOperation(buttonPressed.Operation());
            case CloseBracket, Multiply, Add, Subtract, Divide, Equals -> {
                expression.EnterNumberAndOperation(displayBuffer.Number(), buttonPressed.Operation());
                displayBuffer.Clear();
                if (buttonPressed.Type() == Equals)
                    displayBuffer.Push(expression.Evaluate());
            }
        }
    }

}
