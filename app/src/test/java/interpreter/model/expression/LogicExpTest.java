package interpreter.model.expression;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicExpTest {
    private IDictionary<String, IValue> symTable = new MyDictionary<>();

    private static IExp bol(boolean val){
        return new ValueExp(new BoolValue(val));
    }

    @Test
    void testAnd() throws MyException {
        IExp e = new LogicExp(LogicOp.AND, bol(true), bol(false));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }

    @Test
    void testOr() throws MyException{
        IExp e = new LogicExp(LogicOp.OR, bol(true), bol(false));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }

    @Test
    void nonBoolean() throws MyException{
        IExp e = new LogicExp(LogicOp.AND, bol(true), new ValueExp(new IntValue(7)));
        assertThrows(ExpressionException.class, ()->e.eval(symTable));
    }
}