package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class Calculator extends JFrame implements ActionListener {
    private JButton delBtn, clearBtn;
    public JButton NumberBtn[];
    public JButton functionBtn[];
    public JButton Add_btn, Sub_btn, Mul_btn, Div_btn, equalBtn, decBtn, openBracket_btn, closeBracket_btn;
    private JPanel panel;
    private JTextField text;

    private double number;
    private double result = 0;
    private char operator = ' ';
    private int x = 0;
    private ShuntingYard shuntingYard;
    private ReversePolishNotation reversePolish;

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

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        panel.setSize(new Dimension(250, 250));
        panel.setLocation(new Point(20, 70));

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

        panel.add(NumberBtn[7]);
        panel.add(NumberBtn[8]);
        panel.add(NumberBtn[9]);
        panel.add(functionBtn[0]);

        panel.add(NumberBtn[6]);
        panel.add(NumberBtn[5]);
        panel.add(NumberBtn[4]);
        panel.add(functionBtn[1]);

        panel.add(NumberBtn[3]);
        panel.add(NumberBtn[2]);
        panel.add(NumberBtn[1]);
        panel.add(functionBtn[2]);

        panel.add(functionBtn[7]);
        panel.add(NumberBtn[0]);
        panel.add(functionBtn[4]);
        panel.add(functionBtn[3]);
        panel.add(functionBtn[8]);
        panel.add(functionBtn[9]);

        this.add(functionBtn[6]);
        this.add(functionBtn[5]);
        this.add(text);
        this.add(panel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        shuntingYard = new ShuntingYard();
        reversePolish = new ReversePolishNotation();
    }

    public String valueText() {
        return text.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //flag = false;

        if (e.getSource() == functionBtn[0]) {//addition
            if (!text.getText().isEmpty()) {
                shuntingYard.TypeOperand("+", text.getText());
                x = 1;
            }
        }

        if (e.getSource() == functionBtn[1]) {//substration
            if (!text.getText().isEmpty()) {
                shuntingYard.TypeOperand("-", text.getText());
                x = 1;
            }
        }

        if (e.getSource() == functionBtn[2]) {//multiplication
            if (!text.getText().isEmpty()) {
                shuntingYard.TypeOperand("*", text.getText());
                x = 1;
            }
        }

        if (e.getSource() == functionBtn[3]) {//division
            if (!text.getText().isEmpty()) {
                shuntingYard.TypeOperand("/", text.getText());
                x = 1;
            }
        }

        if (e.getSource() == functionBtn[4]) {// equals operation
            if (!text.getText().isEmpty()) {
                text.setText(calculate());
                x = 3;
            }
        }

        if (e.getSource() == functionBtn[5]) {// del function
            text.setText("");
        }

        if (e.getSource() == functionBtn[6]) {// clear function
            result = 0;
            x = 0;
            number = 0;
            operator = ' ';
            text.setText("");
            shuntingYard.reset();
        }

        if (e.getSource() == functionBtn[7]) { // decimal function

            if (x == 1) {
                text.setText("");

                if (!text.getText().contains(".")) {
                    text.setText(text.getText().concat("."));
                }

                x = 0;
            } else {
                if (!text.getText().contains(".")) {
                    text.setText(text.getText().concat("."));
                }
            }

        }
        if (e.getSource() == functionBtn[8]) { // (
            shuntingYard.TypeOpeningBracket();
        }
        if (e.getSource() == functionBtn[9]) { // (
            if (!text.getText().isEmpty()) {
                shuntingYard.TypeClosingBracket(String.valueOf(Double.valueOf(text.getText())));
            }
        }

        for (int i = 0; i < 10; i++) { // number button
            if (e.getSource() == NumberBtn[i]) {
                if (x == 1) {
                    text.setText("");
                    text.setText(text.getText().concat(String.valueOf(i)));
                    x = 2;
                } else {
                    text.setText(text.getText().concat(String.valueOf(i)));
                }
            }
        }
    }

    private String calculate() {
        shuntingYard.Type(text.getText());
        ArrayList<String> calculationInPolishNotation = shuntingYard.output();
        String calculationResult = reversePolish.calculate(calculationInPolishNotation);
        shuntingYard.reset();
        return calculationResult;
    }

}
