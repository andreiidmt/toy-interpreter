package interpreter.model.adt;

import interpreter.exceptions.ADTException;

public interface IList<T>{
    public void add (T v);
    public T get(int pos) throws ADTException;
    public int size();
}
