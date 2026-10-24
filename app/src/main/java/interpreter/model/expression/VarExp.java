package interpreter.model.expression;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.type.IType;
import interpreter.model.value.IValue;

public record VarExp(String varName) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {
        if (!symTable.isDefined(varName)){
            throw new ExpressionException("Variable " + varName + " isn't declared!");
        }
        return symTable.lookup(varName);
    }

    @Override
    public String toString(){
        return varName;
    }
}
