package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyDictionaryTest {
    private IDictionary<String, Integer> dict;

    @BeforeEach
    void setUp(){
        dict = new MyDictionary<>();
    }

    @Test
    void testPut() throws ADTException {
        dict.put("A", 1);
        assertTrue(dict.isDefined("A"));
    }

    @Test
    void testLookup() throws ADTException{
        dict.put("B", 2);
        assertEquals(2, dict.lookup("B"));
    }

    @Test
    void testUpdate() throws ADTException{
        dict.put("C", 3);
        dict.update("C", 1);
        assertEquals(1, dict.lookup("C"));
    }

    @Test
    void testLookupThrow(){
        assertThrows(ADTException.class, ()->dict.lookup("A"));
    }

    @Test
    void putThrows() throws MyException {
        dict.put("A", 2);
        assertThrows(ADTException.class, ()->dict.put("A",3));
    }

    @Test
    void updateThrows(){
        assertThrows(ADTException.class, ()->dict.update("B",5));
    }
}