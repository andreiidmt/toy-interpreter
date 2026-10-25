package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.expression.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class CompStmtTest {
    private PrgState state;

    @BeforeEach
    void setUp() {
        state = new PrgState(new MyStack<>(), new MyDictionary<>(),
                new MyList<>(), new NopStmt());
    }

    void firstEndsUpOnTopOfSecond() throws MyException {
        IStmt first = new VarDeclStmt("a", new IntType());
        IStmt second = new PrintStmt(new ValueExp(new IntValue(1)));
        new CompStmt(first, second).execute(state);
        assertEquals(first, state.getExeStack().pop());
        assertEquals(second, state.getExeStack().pop());
    }

}