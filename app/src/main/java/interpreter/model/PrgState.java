package interpreter.model;

import interpreter.model.adt.*;
import interpreter.model.statement.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;

import java.io.BufferedReader;
import java.nio.Buffer;

public class PrgState {
    private IStack<IStmt> exeStack;
    private IDictionary<String, IValue> symTable;
    private IList<IValue> out;
    private IDictionary<StringValue, BufferedReader> fileTable;
    private IStmt originalProgram;

    public PrgState(IStack<IStmt> stack, IDictionary<String, IValue> sym,
                    IList<IValue> out, IDictionary<StringValue, BufferedReader> fileTable,
                    IStmt program){
        this.exeStack = stack;
        this.symTable = sym;
        this.out = out;
        this.fileTable = fileTable;
        this.originalProgram = program;
        this.exeStack.push(originalProgram);
    }

    public static PrgState of(IStmt program){
        return new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), program);
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

    public IDictionary<StringValue, BufferedReader> getFileTable(){return this.fileTable;}

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

    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable){
        this.fileTable = fileTable;
    }

    public void setOriginalProgram(IStmt program){
        this.originalProgram = program;
    }

    @Override
    public String toString(){
        return "ExeStack: " + exeStack + "\n" + "SymTable: " + symTable + "\n" + "Out: " + out + "\n";
    }

    public String toLogString(){
        var sb = new StringBuilder();
        sb.append("ExeStack:\n");
        exeStack.toList().forEach(s -> sb.append(s).append("\n"));
        sb.append("SymTable:\n");
        symTable.toMap().forEach((k,v)->sb.append(k).append(" --> ").append(v).append("\n"));
        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append("\n"));
        sb.append("FileTable:\n");
        fileTable.toMap().keySet().forEach(f -> sb.append(f.val()).append("\n"));
        return sb.toString();
    }


}
