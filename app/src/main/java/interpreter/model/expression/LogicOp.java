package interpreter.model.expression;

public enum LogicOp {
    AND("&&"), OR("||");
    private final String symbol;
    LogicOp(String sym){
        this.symbol = sym;
    }
    @Override
    public String toString(){
        return this.symbol;
    }
}
