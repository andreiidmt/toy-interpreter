package interpreter.model.expression;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

public record LogicExp(LogicOp op, IExp left, IExp right) implements IExp {
    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException{
        if (!(left.eval(symTable) instanceof BoolValue(boolean l1))){
            throw new ExpressionException("First operand is not boolean!");
        }
        if (!(right.eval(symTable) instanceof BoolValue(boolean l2))){
            throw new ExpressionException("Second operand is not boolean!");
        }
        return switch (op){
            case AND -> new BoolValue(l1 && l2);
            case OR -> new BoolValue(l1 || l2);
        };
    }

    @Override
    public String toString(){
        return "(" + left + " " + op + " " + right + ")";
    }
}
