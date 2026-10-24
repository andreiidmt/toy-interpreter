package interpreter.model.expression;

import interpreter.exceptions.MyException;
import interpreter.model.value.IValue;

public record ValueExp(IValue value) implements IExp{
    @Override
    public IValue eval(IDictionary<String, IValue> symtable) throws MyException {
        return value;
    }

    @Override
    public String toString(){
        return value.toString();
    }
}
