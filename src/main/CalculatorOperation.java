package main;

public enum CalculatorOperation {
    Add,
    Subtract,
    Multiply,
    Divide,
    Equals,
    Clear, Delete, Decimal, Number, None, CloseBracket, OpenBracket;

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
                return "CLR";
            case Delete:
                return "CE";
            case Decimal:
                return ".";
            case Number:
                return "0";
            case OpenBracket:
                return "(";
            case CloseBracket:
                return ")";
            default:
                return "";
        }
    }
}
