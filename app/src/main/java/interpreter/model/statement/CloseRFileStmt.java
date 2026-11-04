package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.value.StringValue;

import java.io.IOException;

public record CloseRFileStmt(IExp exp) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName))
            throw new StatementException("closeRFile: file name is not a string");
        var fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName))
            throw new StatementException("closeRFile: file is not opened");
        try{
            var reader = fileTable.lookup(fileName);
            reader.close();
            fileTable.remove(fileName);
        }catch(IOException e){
            throw new StatementException("closeRFile: couldn't close " + fileName + ": " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString(){
        return "closeRFile("+exp+")";
    }
}
