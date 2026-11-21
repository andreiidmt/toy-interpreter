package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.value.BoolValue;

public record WhileStmt(IExp cond, IStmt body) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (!(cond.eval(state.getSymTable(), state.getHeap()) instanceof BoolValue bol))
            throw new StatementException("while: " + cond + " is not of BoolType");
        if (bol.val()){
            state.getExeStack().push(this);
            state.getExeStack().push(body);
        }
        return state;
    }

    @Override
    public String toString(){
        return "(while (" + cond + ") " + body + ")";
    }
}
