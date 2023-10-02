package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import static main.CalculatorOperation.Equals;

public class Calculator extends JFrame implements ActionListener {
    private JButton delBtn, clearBtn;
    public JButton NumberBtn[];
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

        NumberBtn = new JButton[10];

        for (int i = 0; i < 10; i++) {
            NumberBtn[i] = new JButton(String.valueOf(i));
            NumberBtn[i].setFocusable(false);
            NumberBtn[i].setFont(new Font("Orbitron", Font.PLAIN, 20));
            NumberBtn[i].setSize(new Dimension(10, 10));
            NumberBtn[i].setVerticalTextPosition(JButton.CENTER);
            NumberBtn[i].setHorizontalTextPosition(JButton.CENTER);
            NumberBtn[i].addActionListener(this);
            NumberBtn[i].setForeground(Color.black);
        }
        // Start here ...
        // Objective - add a SIN button
        keypad.add(CreateNumberButton(7));
        keypad.add(CreateNumberButton(8));
        keypad.add(CreateNumberButton(9));
        keypad.add(CreateFunctionButton("+"));

        keypad.add(CreateNumberButton(6));
        keypad.add(CreateNumberButton(5));
        keypad.add(CreateNumberButton(4));
        keypad.add(CreateFunctionButton("-"));

        keypad.add(CreateNumberButton(3));
        keypad.add(CreateNumberButton(2));
        keypad.add(CreateNumberButton(1));
        keypad.add(CreateFunctionButton("*"));

        keypad.add(CreateFunctionButton("."));
        keypad.add(CreateNumberButton(0));
        keypad.add(CreateFunctionButton("="));
        keypad.add(CreateFunctionButton("/"));
        keypad.add(CreateFunctionButton("("));
        keypad.add(CreateFunctionButton(")"));

        this.add(functionBtn[6]);
        this.add(functionBtn[5]);
        this.add(text);
        this.add(keypad);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        expression = new ReversePolishNotationExpression();
        displayBuffer = new DisplayBuffer(text);
    }

    private JButton CreateNumberButton(int i) {
        JButton numberButton = CreateButton(String.valueOf(i));
        numberButton.setSize(new Dimension(10, 10));
        return numberButton;
    }

    private JButton CreateFunctionButton(String s) {
        JButton functionButton = CreateButton(s);
        functionButton.setSize(new Dimension(100, 30));
        return functionButton;
    }

    private JButton CreateButton(String s) {
        JButton functionButton = new JButton(s);
        functionButton.setFocusable(false);
        functionButton.setVerticalTextPosition(JButton.CENTER);
        functionButton.setHorizontalTextPosition(JButton.CENTER);
        functionButton.setFont(new Font("Orbitron", Font.PLAIN, 20));
        functionButton.addActionListener(this);
        functionButton.setForeground(Color.black);
        return functionButton;
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
