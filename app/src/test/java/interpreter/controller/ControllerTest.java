package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.statement.IStmt;
import interpreter.model.statement.NopStmt;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;
import interpreter.view.Examples;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    @TempDir
    Path dir;

    private PrgState run(IStmt program) throws MyException {
        PrgState state = PrgState.of(program);
        var repo = new Repository(state, dir.resolve("log.txt").toString());
        new Controller(repo, false).allStep();
        return state;
    }

    @Test
    void example1() throws MyException {
        PrgState state = run(Examples.ex1);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void example2() throws MyException {
        PrgState state = run(Examples.ex2);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(18), state.getOut().get(0));
    }

    @Test
    void example3() throws MyException {
        PrgState state = run(Examples.ex3);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals(new IntValue(2), state.getOut().get(0));
    }

    @Test
    void oneStepOnEmptyStackThrows() throws MyException {
        PrgState state = PrgState.of(new NopStmt());
        Controller controller = new Controller(new Repository(state, dir.resolve("log.txt").toString()), false);
        controller.oneStep(state);
        assertThrows(StatementException.class, () -> controller.oneStep(state));
    }

    @Test
    void fileExampleReadsBothNumbersAndClosesTheFile() throws Exception{
        Path in = dir.resolve("test.in");
        Files.writeString(in, "15\n50\n");
        PrgState s = run(Examples.fileExample(in.toString()));
        assertEquals(new IntValue(15), s.getOut().get(0));
        assertEquals(new IntValue(50), s.getOut().get(1));
        assertTrue(s.getFileTable().toMap().isEmpty());
    }

    @Test
    void logFileContainsTheFourSectionsInOrder() throws Exception{
        run(Examples.ex1);
        String log = Files.readString(dir.resolve("log.txt"));
        int a = log.indexOf("ExeStack:"), b = log.indexOf("SymTable:"),
                c = log.indexOf("Out:"), d = log.indexOf("FileTable:");
        assertTrue(0 <= a && a < b && b < c && c < d);
    }

    @Test
    void twoZeroEmptyFile() throws Exception{
        Path in = dir.resolve("test.in");
        Files.writeString(in, "");
        PrgState s = run(Examples.fileExample(in.toString()));
        assertEquals(new IntValue(0), s.getOut().get(0));
        assertEquals(new IntValue(0), s.getOut().get(1));
    }

    @Test
    void ex5Prints5() throws MyException{
        PrgState s = run(Examples.ex5);
        assertEquals(new IntValue(5), s.getOut().get(0));
    }

}