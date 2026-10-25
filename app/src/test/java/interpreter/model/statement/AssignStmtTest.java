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

class AssignStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp(){
        state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new NopStmt());
    }

    @Test
    void assignUpdatesVariable() throws MyException {
        new VarDeclStmt("v", new IntType()).execute(state);
        new AssignStmt("v", new ValueExp(new IntValue(2))).execute(state);
        assertEquals(new IntValue(2), state.getSymTable().lookup("v"));
    }

    @Test
    void assignThrows(){
        var stmt = new AssignStmt("v", new ValueExp(new IntValue(2)));
        assertThrows(StatementException.class, ()->stmt.execute(state));
    }

    @Test
    void assignWithTypeMismatchThrows() throws MyException {
        new VarDeclStmt("v", new IntType()).execute(state);
        var stmt = new AssignStmt("v", new ValueExp(new BoolValue(true)));
        assertThrows(StatementException.class, () -> stmt.execute(state));
    }
}