package interpreter.model.statement;

import interpreter.exceptions.MyException;

public record NopStmt() implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public String toString(){
        return "nop";
    }
}
