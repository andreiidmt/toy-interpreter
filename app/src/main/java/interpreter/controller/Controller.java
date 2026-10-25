package interpreter.controller;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.statement.IStmt;
import interpreter.repository.IRepository;

public class Controller {
    private IRepository repo;
    private boolean displayFlag;

    public Controller(IRepository repo, boolean flag){
        this.repo = repo;
        this.displayFlag = flag;
    }

    public PrgState oneStep(PrgState state) throws MyException {
        var stack = state.getExeStack();
        if (stack.isEmpty()){
            throw new StatementException("Program state ExeStack is empty.");
        }
        IStmt crtStmt = stack.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException{
        PrgState prg = repo.getCrtPrg();
        display(prg);
        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            display(prg);
        }
    }

    public void display(PrgState prg){
        if (!displayFlag)
            return;
        System.out.println(prg);
    }
}
