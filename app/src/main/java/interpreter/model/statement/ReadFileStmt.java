package interpreter.model.statement;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import java.io.IOException;

public record ReadFileStmt(IExp exp, String varName) implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException{
        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName))
            throw new StatementException("readFile: " + exp + " is not a string");
        var fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName))
            throw new StatementException("readFile: " + fileName + " isn't opened");
        if (!(state.getSymTable().isDefined(varName)))
            throw new StatementException("readFile: " + varName + " is not a declared variable");
        if (!(state.getSymTable().lookup(varName).getType() instanceof IntType))
            throw new StatementException("readFile: " + varName + " is not of type int");
        try{
            var reader = fileTable.lookup(fileName);
            var line = reader.readLine();
            if (line == null){
                state.getSymTable().update(varName, new IntValue(0));
            }
            else{
                var actualValue = Integer.parseInt(line.strip());
                state.getSymTable().update(varName, new IntValue(actualValue));
            }
        }catch(IOException e){
            throw new StatementException("readFile: couldn't read from " + fileName  +": " + e.getMessage());
        }
        catch(NumberFormatException e){
            throw new StatementException("readFile: not an integer" +": " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString(){
        return "readFile(" + exp + ", " + varName +")";
    }
}
