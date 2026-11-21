package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.*;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhileStmtTest {
    private static IStmt countdown(int start) {
        return new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("count", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(start))),
                                new WhileStmt(
                                        new RelationalExp(RelOP.GT, new VarExp("v"), new ValueExp(new IntValue(0))),
                                        new CompStmt(new AssignStmt("v", new ArithExp(ArithOp.SUB,
                                                new VarExp("v"), new ValueExp(new IntValue(1)))),
                                                new AssignStmt("count", new ArithExp(ArithOp.ADD,
                                                        new VarExp("count"), new ValueExp(new IntValue(1)))))))));
    }

    private static PrgState runAll(IStmt program) throws MyException {
        PrgState state = PrgState.of(program);
        while (!state.getExeStack().isEmpty())
            state.getExeStack().pop().execute(state);
        return state;
    }

    @Test
    void loopRunsSeveralTimes () throws MyException{
        PrgState state = runAll(countdown(3));
        assertEquals(new IntValue(3), state.getSymTable().lookup("count"));
    }

    @Test
    void loopRuns1Time () throws MyException{
        PrgState state = runAll(countdown(1));
        assertEquals(new IntValue(1), state.getSymTable().lookup("count"));
    }

    @Test
    void loopRuns0 () throws MyException{
        PrgState state = runAll(countdown(0));
        assertEquals(new IntValue(0), state.getSymTable().lookup("count"));
    }

    @Test
    void nonBooleanConditionThrows() {
        PrgState state = PrgState.of(new NopStmt());
        var loop = new WhileStmt(new ValueExp(new IntValue(1)), new NopStmt());
        assertThrows(StatementException.class, () -> loop.execute(state));
    }
}
