package interpreter.model.expression;

public enum ArithOp {
    ADD("+"), SUB("-"), MUL("-"), DIV("/");
    private final String symbol;
    ArithOp(String sym){
        this.symbol = sym;
    }
    @Override
    public String toString(){
        return this.symbol;
    }
}
