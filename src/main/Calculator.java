package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.CalculatorOperation.Equals;

public class Calculator extends JFrame implements ActionListener {
    private JButton delBtn, clearBtn;
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

        Add_btn = new JButton("+");
        Sub_btn = new JButton("-");
        Mul_btn = new JButton("*");
        Div_btn = new JButton("/");
        equalBtn = new JButton("=");
        decBtn = new JButton(".");
        openBracket_btn = new JButton("(");
        closeBracket_btn = new JButton(")");

        clearBtn = new JButton("CE");
        clearBtn.setLocation(new Point(20, 330));

        delBtn = new JButton("CLR");
        delBtn.setLocation(new Point(170, 330));

        functionBtn = new JButton[10];

        functionBtn[0] = Add_btn;
        functionBtn[1] = Sub_btn;
        functionBtn[2] = Mul_btn;
        functionBtn[3] = Div_btn;
        functionBtn[4] = equalBtn;
        functionBtn[5] = delBtn;
        functionBtn[6] = clearBtn;
        functionBtn[7] = decBtn;
        functionBtn[8] = openBracket_btn;
        functionBtn[9] = closeBracket_btn;

        for (int i = 0; i < 10; i++) {
            functionBtn[i].setFocusable(false);
            functionBtn[i].setVerticalTextPosition(JButton.CENTER);
            functionBtn[i].setHorizontalTextPosition(JButton.CENTER);
            functionBtn[i].setFont(new Font("Orbitron", Font.PLAIN, 20));
            functionBtn[i].setSize(new Dimension(100, 30));
            functionBtn[i].addActionListener(this);
            functionBtn[i].setForeground(Color.black);
        }

        NumberButtons = new JButton[10];

        for (int i = 0; i < 10; i++) {
            NumberButtons[i] = new JButton(String.valueOf(i));
            NumberButtons[i].setFocusable(false);
            NumberButtons[i].setFont(new Font("Orbitron", Font.PLAIN, 20));
            NumberButtons[i].setSize(new Dimension(10, 10));
            NumberButtons[i].setVerticalTextPosition(JButton.CENTER);
            NumberButtons[i].setHorizontalTextPosition(JButton.CENTER);
            NumberButtons[i].addActionListener(this);
            NumberButtons[i].setForeground(Color.black);
        }

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

        this.add(functionBtn[6]);
        this.add(functionBtn[5]);
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

    private JButton CreateMultiplyButton() {
        JButton button = new JButton("*");
        button.setFocusable(false);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Orbitron", Font.PLAIN, 20));
        button.setSize(new Dimension(100, 30));
        button.addActionListener(this);
        button.setForeground(Color.black);
        return button;
    }

    private JButton CreateSubtractButton() {
        JButton button = new JButton("-");
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

    private JButton CreateAddButton() {
        JButton button = new JButton("+");
        button.setFocusable(false);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Orbitron", Font.PLAIN, 20));
        button.setSize(new Dimension(100, 30));
        button.addActionListener(this);
        button.setForeground(Color.black);
        return button;
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
