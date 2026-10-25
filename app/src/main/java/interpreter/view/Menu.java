package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.adt.MyStack;
import interpreter.model.statement.IStmt;
import interpreter.repository.Repository;

import java.util.Scanner;

public class Menu {
    private Scanner in =  new Scanner(System.in);
    private IStmt selected = null;
    private int selectedEx = 0;

    public void run(){
        while(true){
            printMenu();
            String choice = in.nextLine().trim();
            switch(choice){
                case "1" -> selectProgram();
                case "2" -> runProgram();
                case "0" -> {return;}
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void printMenu(){
        System.out.println("---------");
        System.out.println("1. Select a program" + (selected == null ? "" :  " (current: Ex" + selectedEx + ")"));
        System.out.println("2. Run selected program");
        System.out.println("0. Exit");
        System.out.println("Enter a number: ");

    }

    public void selectProgram(){
        System.out.println("1. " + Examples.ex1);
        System.out.println("2. " + Examples.ex2);
        System.out.println("3. " + Examples.ex3);
        System.out.println("Please enter a number: ");
        switch(in.nextLine().trim()){
            case "1" -> {selected = Examples.ex1; selectedEx = 1;}
            case "2" -> {selected = Examples.ex2; selectedEx = 2;}
            case "3" -> {selected = Examples.ex3; selectedEx = 3;}
            default -> System.out.println("Invalid choice.");
        }
    }

    public void runProgram(){
        if (selected == null){
            System.out.println("Select a program first.");
            return;
        }
        PrgState state = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), selected);

        Controller cont = new Controller(new Repository(state), true);
        try{
            cont.allStep();
        }
        catch(MyException e){
            System.out.println(e.getMessage());
        }
    }
}
