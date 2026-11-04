package interpreter.model.statement;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.expression.ValueExp;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileStmtTest {

    @TempDir
    Path dir;

    private PrgState state;
    private ValueExp file;

    @BeforeEach
    void setUp() throws Exception{
        Path f = dir.resolve("numbers.in");
        Files.writeString(f, "7\n");
        file = new ValueExp(new StringValue(f.toString()));
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("x", new IntType()).execute(state);
    }

    @AfterEach
    void closeOpenFiles() throws Exception {
        for (var reader : state.getFileTable().toMap().values())
            reader.close();
    }

    @Test
    void readGivesTheNextNumberThenZeroAtTheEnd() throws MyException {
        new OpenRFileStmt(file).execute(state);
        new ReadFileStmt(file, "x").execute(state);
        assertEquals(new IntValue(7), state.getSymTable().lookup("x"));
        new ReadFileStmt(file, "x").execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));
    }

    @Test
    void openingTheSameFileTwiceThrows() throws MyException {
        new OpenRFileStmt(file).execute(state);
        assertThrows(StatementException.class,
                () -> new OpenRFileStmt(file).execute(state));
    }

    @Test
    void openingMissingFileThrows() throws MyException{
        var ex = new OpenRFileStmt(new ValueExp(new StringValue(dir.resolve("missing.in").toString())));
        assertThrows(StatementException.class, () -> ex.execute(state));
    }

    @Test
    void nonStringExpressionThrows() throws MyException{
        var ex = new OpenRFileStmt(new ValueExp(new IntValue(5)));
        assertThrows(StatementException.class, () -> ex.execute(state));
    }

    @Test
    void addsFileToFileTable() throws MyException{
        new OpenRFileStmt(file).execute(state);
        assertTrue(state.getFileTable().isDefined((StringValue)file.value()));
    }

    @Test
    void variableNotDeclaredThrows() throws MyException{
        new OpenRFileStmt(file).execute(state);
        var ex = new ReadFileStmt(file, "a");
        assertThrows(StatementException.class, () -> ex.execute(state));
    }

    @Test
    void variableNotIntThrows() throws MyException{
        new VarDeclStmt("a", new BoolType()).execute(state);
        new OpenRFileStmt(file).execute(state);
        var ex = new ReadFileStmt(file, "a");
        assertThrows(StatementException.class, () -> ex.execute(state));
    }

    @Test
    void fileNotOpenThrows() throws MyException{
        var ex = new ReadFileStmt(file, "x");
        assertThrows(StatementException.class, () -> ex.execute(state));
    }

    @Test
    void nonIntegerLineThrows() throws Exception{
        Path bad = dir.resolve("bad.in");
        Files.writeString(bad, "abc\n");
        var badFile = new ValueExp(new StringValue(bad.toString()));
        new OpenRFileStmt(badFile).execute(state);
        assertThrows(StatementException.class, () -> new ReadFileStmt(badFile, "x").execute(state));
    }

    @Test
    void removesFile() throws MyException{
        new OpenRFileStmt(file).execute(state);
        new CloseRFileStmt(file).execute(state);
        assertFalse(state.getFileTable().isDefined((StringValue) file.value()));
    }

    @Test
    void closeNotOpenFileThrows() throws MyException{
        var ex = new CloseRFileStmt(file);
        assertThrows(StatementException.class, () -> ex.execute(state));
    }



}