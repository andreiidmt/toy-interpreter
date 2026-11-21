package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

public record NewStmt(String varName, IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state)throws MyException{
        var symTable = state.getSymTable();
        if (!symTable.isDefined(varName))
            throw new StatementException("new: " + varName + " is not declared");
        if (!(symTable.lookup(varName)instanceof RefValue ref))
            throw new StatementException("new: " + varName + " is not a reference");
        IValue val = exp.eval(symTable, state.getHeap());
        if (!val.getType().equals(ref.locationType()))
            throw new StatementException("new: " + exp + " has type " + val.getType()
                    + ", expected " + ref.locationType());
        int address = state.getHeap().allocate(val);
        symTable.update(varName, new RefValue(address, ref.locationType()));
        return state;
    }
    @Override
    public String toString(){
        return "new(" + varName + ", " + exp + ")";
    }
}
