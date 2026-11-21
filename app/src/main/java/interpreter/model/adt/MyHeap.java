package interpreter.model.adt;

import interpreter.model.value.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap  implements IHeap{
    private Map<Integer, IValue> heap = new HashMap<>();
    private int nextFree = 1;

    @Override
    public int allocate(IValue value){
        
    }
}
