package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
