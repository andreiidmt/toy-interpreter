package interpreter.model.type;

import interpreter.model.value.BoolValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolTypeTest {
    @Test
    void typeIsBool(){
        assertEquals(new BoolType(), new BoolValue(true).getType());
    }
}