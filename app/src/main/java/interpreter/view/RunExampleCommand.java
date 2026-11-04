package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;

public class RunExampleCommand extends Command{
    private Controller controller;
    private boolean executed = false;

    public RunExampleCommand(String key, String description, Controller controller){
        this.controller = controller;
        super(key, description);
    }

    @Override
    public void execute(){
        if (executed){
            System.out.println("This example has already been executed");
            return;
        }
        executed = true;
        try{
            controller.allStep();
        }catch(MyException e){
            System.out.print("Error: " + e.getMessage());
        }
    }
}
