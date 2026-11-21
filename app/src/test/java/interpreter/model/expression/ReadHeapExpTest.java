package interpreter.model.expression;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.statement.NewStmt;
import interpreter.model.statement.NopStmt;
import interpreter.model.statement.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadHeapExpTest {
    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void readsTheValueAtTheAddress() throws MyException {
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);
        IExp e = new ReadHeapExp(new VarExp("v"));
        assertEquals(new IntValue(20), e.eval(state.getSymTable(), state.getHeap()));
    }

    @Test
    void NonReferenceThrows() throws MyException{
        new VarDeclStmt("a", new IntType()).execute(state);
        IExp e = new ReadHeapExp(new VarExp("a"));
        assertThrows(ExpressionException.class, ()->e.eval(state.getSymTable(), state.getHeap()));
    }

    @Test
    void addressNotInHeapThrows() throws MyException{
        IExp e = new ReadHeapExp(new VarExp("v"));
        assertThrows(ExpressionException.class, () -> e.eval(state.getSymTable(), state.getHeap()));
    }
}
