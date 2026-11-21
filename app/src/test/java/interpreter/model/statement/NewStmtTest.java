package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void newAllocatesAtAddressOneAndUpdatesTheVariable() throws MyException {
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        assertEquals(new RefValue(1, new IntType()), state.getSymTable().lookup("v"));
        assertEquals(new IntValue(20), state.getHeap().get(1));
    }

    @Test
    void variableNotDeclaredThrows() throws MyException{
        var exec = new NewStmt("a", new ValueExp(new IntValue(20)));
        assertThrows(StatementException.class, () -> exec.execute(state));
    }

    @Test
    void variableNotRefThrows() throws MyException{
        new VarDeclStmt("a", new IntType()).execute(state);
        var exec = new NewStmt("a", new ValueExp(new IntValue(20)));
        assertThrows(StatementException.class, () ->exec.execute(state));
    }

    @Test
    void variableValueOfTheWrongTypeThrows() throws MyException{
        var exec = new NewStmt("v", new ValueExp(new BoolValue(true)));
        assertThrows(StatementException.class, () -> exec.execute(state));
    }
}
