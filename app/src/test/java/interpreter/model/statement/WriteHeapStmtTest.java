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

class WriteHeapStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void writeChangesTheHeapLocationButNotTheVariable() throws MyException {
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        new WriteHeapStmt("v", new ValueExp(new IntValue(30))).execute(state);
        assertEquals(new IntValue(30), state.getHeap().get(1));
        assertEquals(new RefValue(1, new IntType()), state.getSymTable().lookup("v"));
    }

    @Test
    void notReferenceThrows() throws MyException{
        new VarDeclStmt("a", new IntType()).execute(state);
        var exec = new WriteHeapStmt("a", new ValueExp(new IntValue(30)));
        assertThrows(StatementException.class, ()->exec.execute(state));
    }

    @Test
    void addressNotInHeap() throws MyException {
        var exec = new WriteHeapStmt("v", new ValueExp(new IntValue(30)));
        assertThrows(StatementException.class, () -> exec.execute(state));
    }

    @Test
    void valueOfWrongType() throws MyException{
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        var exec =new WriteHeapStmt("v", new ValueExp(new BoolValue(true)));
        assertThrows(StatementException.class, () -> exec.execute(state));
    }
}
