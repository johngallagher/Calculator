package main;

import javax.swing.*;

public class DisplayBuffer {
    private final JTextField text;

    public DisplayBuffer(JTextField text) {
        this.text = text;
    }

    public String Pop() {
        String displayText = this.text.getText();
        this.text.setText("");
        return displayText;
    }

    public void Append(String value) {
        this.text.setText(this.text.getText() + value);
    }

    public void Clear() {
        this.text.setText("");
    }

    public void Push(String value) {
        this.text.setText(value);
    }
}
