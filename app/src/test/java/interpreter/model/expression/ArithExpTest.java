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

class ArithExpTest {
    private IDictionary<String, IValue> symTable = new MyDictionary<>();

    private static IExp num(int n){
        return new ValueExp(new IntValue(n));
    }

    @Test
    void correctAddition() throws MyException {
        IExp e = new ArithExp(ArithOp.ADD, num(7), num(8));
        assertEquals(new IntValue(15), e.eval(symTable));
    }

    @Test
    void correctSubtraction() throws MyException{
        IExp e = new ArithExp(ArithOp.SUB, num(7), num(8));
        assertEquals(new IntValue(-1), e.eval(symTable));
    }

    @Test
    void correctMultiplication() throws MyException{
        IExp e = new ArithExp(ArithOp.MUL, num(7), num(8));
        assertEquals(new IntValue(56), e.eval(symTable));
    }

    @Test
    void correctDivision() throws MyException{
        IExp e = new ArithExp(ArithOp.DIV, num(15), num(3));
        assertEquals(new IntValue(5), e.eval(symTable));
    }

    @Test
    void divisionByZero() throws MyException{
        IExp e = new ArithExp(ArithOp.DIV, num(1), num(0));
        var ex = assertThrows(ExpressionException.class, () -> e.eval(symTable));
        assertEquals("Division by zero.", ex.getMessage());
    }

    @Test
    void nonIntegerOperand() throws MyException{
        IExp e = new ArithExp(ArithOp.ADD, num(1), new ValueExp(new BoolValue(true)));
        assertThrows(ExpressionException.class, () -> e.eval(symTable));
    }

    @Test
    void undefinedVariable() throws MyException{
        IExp e = new ArithExp(ArithOp.ADD, num(1), new VarExp("a"));
        assertThrows(ExpressionException.class, () -> e.eval(symTable));
    }
}