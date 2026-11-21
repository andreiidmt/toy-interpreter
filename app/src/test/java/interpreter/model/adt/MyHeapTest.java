package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import interpreter.exceptions.MyException;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {
    private IHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MyHeap();
    }

    @Test
    void allocateReturnsConsecutiveAddressesStartingAtOne() {
        assertEquals(1, heap.allocate(new IntValue(10)));
        assertEquals(2, heap.allocate(new IntValue(20)));
        assertEquals(3, heap.allocate(new IntValue(30)));
    }

    @Test
    void missingGetThrows() throws MyException {
        heap.allocate(new IntValue(20));
        assertThrows(ADTException.class, ()->heap.get(5));

    }

    @Test
    void missingUpdateThrows() throws MyException{
        heap.allocate(new IntValue(20));
        assertThrows(ADTException.class, ()->heap.update(2, new IntValue(30)));
    }
}
