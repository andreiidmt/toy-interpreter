package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    private IStack<Integer> stack;

    @BeforeEach
    void setUp(){
        stack = new MyStack<>();
    }

    @Test
    void LIFO()throws ADTException {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void emptyPopThrows() throws ADTException{
        assertThrows(ADTException.class, ()->stack.pop());
    }


}