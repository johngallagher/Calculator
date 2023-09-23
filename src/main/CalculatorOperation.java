package main;

public enum CalculatorOperation {
    Add,
    Subtract,
    Multiply,
    Divide,
    Equals,
    Clear, Delete, Decimal, Number, None;

    public String getSymbol() {
        switch (this) {
            case Add:
                return "+";
            case Subtract:
                return "-";
            case Multiply:
                return "*";
            case Divide:
                return "/";
            case Equals:
                return "=";
            case Clear:
                return "C";
            case Delete:
                return "DEL";
            case Decimal:
                return ".";
            case Number:
                return "0";
            default:
                return "";
        }
    }
}
