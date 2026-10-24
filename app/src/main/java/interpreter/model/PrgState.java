package interpreter.model;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IList;
import interpreter.model.adt.IStack;
import interpreter.model.statement.IStmt;
import interpreter.model.value.IValue;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private IStmt originalProgram;

    public PrgState(IStack<IStmt> stack, IDictionary<String, IValue> sym,
                    IList<IValue> out, IStmt program){
        this.exeStack = stack;
        this.symTable = sym;
        this.out = out;
        this.originalProgram = program;
        this.exeStack.push(originalProgram);
    }

    public IStack<IStmt> getExeStack(){
        return this.exeStack;
    }

    public IDictionary<String, IValue> getSymTable(){
        return this.symTable;
    }

    public IList<IValue> getOut(){
        return this.out;
    }

    public IStmt getOriginalProgram(){
        return this.originalProgram;
    }

    public void setExeStack(IStack<IStmt> stack){
        this.exeStack = stack;
    }

    public void setSymTable(IDictionary<String, IValue> sym){
        this.symTable = sym;
    }

    public void setOut(IList<IValue> out){
        this.out = out;
    }

    public void setOriginalProgram(IStmt program){
        this.originalProgram = program;
    }

    @Override
    public String toString(){
        return "ExeStack: " + exeStack + "\n" + "SymTable: " + symTable + "\n" + "Out: " + out + "\n";
    }


}
