package interpreter.model.statement;

import interpreter.exceptions.MyException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
