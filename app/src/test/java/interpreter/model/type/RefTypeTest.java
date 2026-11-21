package interpreter.model.type;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.statement.NopStmt;
import interpreter.model.statement.VarDeclStmt;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefTypeTest {
    @Test
    void refVariableGetsTheNullReference() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
        assertEquals(new RefValue(0, new IntType()), state.getSymTable().lookup("v"));
    }

    @Test
    void refRefIntDiffers() throws MyException{
        assertNotEquals((new RefType(new RefType(new IntType()))), new RefType(new IntType()));
    }

    @Test
    void refRefIntEquals() throws MyException{
        assertEquals(new RefType(new RefType(new IntType())), new RefType(new RefType(new IntType())));
    }
}
