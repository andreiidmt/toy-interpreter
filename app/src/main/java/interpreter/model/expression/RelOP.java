package interpreter.model.expression;

public enum RelOP {
    LT("<"), LE("<="), EQ("=="), NE("!="), GT(">"), GE(">=");
    private final String symbol;
    RelOP(String s){
        this.symbol = s;
    }
    @Override
    public String toString(){
        return symbol;
    }

}
