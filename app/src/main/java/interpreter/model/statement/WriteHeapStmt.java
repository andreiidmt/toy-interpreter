package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

public record WriteHeapStmt(String varName, IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (!state.getSymTable().isDefined(varName))
            throw new StatementException("wH: " + varName + " is not defined");
        if (!(state.getSymTable().lookup(varName)instanceof RefValue ref))
            throw new StatementException("wH: " + varName + " is not a reference.");
        if (!(state.getHeap().isDefined(ref.address())))
            throw new StatementException("wH: " + ref.address() + " is not in the heap");

        IValue val = exp.eval(state.getSymTable(), state.getHeap());
        if (!val.getType().equals(ref.locationType()))
            throw new StatementException("wH: " + val + " has type: " + val.getType() +
                    ", expected: " + ref.locationType());
        state.getHeap().update(ref.address(), val);
        return state;
    }

    @Override
    public String toString(){
        return "wH(" + varName + ", " + exp + ")";
    }
}
