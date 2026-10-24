package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.model.expression.IExp;

public record PrintStmt(IExp exp) implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var out = state.getOut();
        out.add(exp.eval(state.getSymbolTable()));
        return state;
    }

    @Override
    public String toString(){
        return "print(" + exp + ")";
    }
}
