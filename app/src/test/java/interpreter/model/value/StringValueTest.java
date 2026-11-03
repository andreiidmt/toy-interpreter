package interpreter.model.value;

import interpreter.exceptions.MyException;
import interpreter.model.statement.VarDeclStmt;
import interpreter.model.type.StringType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueTest {



    @Test
    public void twoEqualStrings(){
        var a = new StringValue("check");
        var b = new StringValue("check");
        assertEquals(a,b);
        assertEquals(a.hashCode(), b.hashCode());
    }

}