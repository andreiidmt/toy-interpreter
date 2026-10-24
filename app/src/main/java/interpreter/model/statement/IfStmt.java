package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.value.BoolValue;

public record IfStmt(IExp cond, IStmt thenS, IStmt elseS) implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (!(cond.eval(state.getSymTable()) instanceof BoolValue(boolean val))){
            throw new StatementException("Condition is not boolean.");
        }

        if (val){
            state.getExeStack().push(thenS);
        }
        else{
            state.getExeStack().push(elseS);
        }
        return state;
    }

    @Override
    public String toString(){
        return "if " + cond + " then " + thenS + " else " + elseS;
    }

}
