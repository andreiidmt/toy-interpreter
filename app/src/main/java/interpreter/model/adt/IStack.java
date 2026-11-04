package interpreter.model.adt;

import interpreter.exceptions.ADTException;

import java.util.List;

public interface IStack<T> {
    void push(T v);
    T pop() throws ADTException;
    boolean isEmpty();
    public List<T> toList();

}
