package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.expression.IExp;
import interpreter.model.value.IValue;

public record AssignStmt(String id, IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();
        if (!symTable.isDefined(id)){
            throw new StatementException("Variable " + id + " isn't declared.");
        }
        IValue val = exp.eval(symTable);
        if (!val.getType().equals(symTable.lookup(id).getType())){
            throw new StatementException("Declared type of variable " + id + " and type of " +
                    " the assigned variable does not match.");
        }
        symTable.update(id, val);
        return state;
    }

    @Override
    public String toString(){
        return id + " = " + exp;
    }
}
