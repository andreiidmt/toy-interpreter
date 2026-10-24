package interpreter.model.adt;

import interpreter.exceptions.ADTException;

public interface IDictionary<K,V>{
    public boolean isDefined(K key);
    public V lookup(K key) throws ADTException;
    public void put (K key, V value) throws ADTException;
    public void update(K key, V value) throws ADTException;

}
