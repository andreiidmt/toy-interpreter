package interpreter.model.adt;

import interpreter.exceptions.ADTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    private IList<Integer> list;

    @BeforeEach
    void setUp(){
        list = new MyList<>();
    }

    @Test
    void testAdd() throws ADTException {
        list.add(6);
        assertEquals(6, list.get(0));
    }

    @Test
    void testSize() throws ADTException{
        list.add(6);
        list.add(6);
        list.add(6);
        assertEquals(3, list.size());
    }

    @Test
    void testGetThrows(){
        assertThrows(ADTException.class, ()->list.get(-1));
    }

    @Test
    void testGet() throws ADTException{
        list.add(5);
        list.add(6);
        list.add(7);
        assertEquals(6, list.get(1));
    }

}