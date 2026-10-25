package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.expression.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IfStmtTest {
    private PrgState state;
    private IStmt thenS = new VarDeclStmt("t", new IntType());;
    private IStmt elseS = new VarDeclStmt("e", new IntType());;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(),
                new MyList<>(), new NopStmt());
    }

    @Test
    void nonBooleanConditionThrows() {
        var stmt = new IfStmt(new ValueExp(new IntValue(1)), thenS, elseS);
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }

    @Test
    void trueTest() throws MyException {
        new IfStmt(new ValueExp(new BoolValue(true)), thenS, elseS).execute(state);
        assertEquals(thenS, state.getExeStack().pop());
    }
}