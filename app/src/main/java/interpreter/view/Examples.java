package interpreter.view;

import interpreter.model.expression.*;
import interpreter.model.statement.*;
import interpreter.model.type.BoolType;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.type.StringType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import static interpreter.model.expression.RelOP.LT;

public class Examples {
    public static IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarExp("v"))));

    public static IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
            new CompStmt(new VarDeclStmt("b", new IntType()),
                    new CompStmt(new AssignStmt("a",
                            new ArithExp(ArithOp.ADD, new ValueExp(new IntValue(2)),
                                    new ArithExp(ArithOp.MUL, new ValueExp(new IntValue(3)),
                                            new ValueExp(new IntValue(5))))),
                            new CompStmt(new AssignStmt("b",
                                    new ArithExp(ArithOp.ADD, new VarExp("a"),
                                            new ValueExp(new IntValue(1)))),
                                    new PrintStmt(new VarExp("b"))))));

    public static IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),
                                    new AssignStmt("v", new ValueExp(new IntValue(2))),
                                    new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                    new PrintStmt(new VarExp("v"))))));

    public static IStmt fileExample(String fileName) {
        return new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue(fileName))),
                        new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                new CompStmt(new VarDeclStmt("varc", new IntType()),
                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                        new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CloseRFileStmt(new VarExp("varf"))))))))));
    }

    public static IStmt ex4 = fileExample("test.in");

    public static IStmt ex5 = new CompStmt(new VarDeclStmt("a", new IntType()),
                                 new CompStmt(new VarDeclStmt("b", new IntType()),
                                         new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(5))),
                                                 new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(7))),
                                                         new IfStmt(new RelationalExp(LT, new VarExp("a"),
                                                                 new VarExp("b")),
                                                                    new PrintStmt(new VarExp("a")),
                                                                        new PrintStmt(new VarExp("b")))))));

    public static IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new PrintStmt(new VarExp("v")),
                                            new PrintStmt(new VarExp("a")))))));

    public static IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                            new PrintStmt(new ArithExp(ArithOp.ADD,
                                                    new ReadHeapExp(new ReadHeapExp(new VarExp("a"))),
                                                    new ValueExp(new IntValue(5)))))))));

    public static IStmt ex8 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                            new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                    new PrintStmt(new ArithExp(ArithOp.ADD,
                                            new ReadHeapExp(new VarExp("v")),
                                            new ValueExp(new IntValue(5))))))));

    public static IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                    new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                            new CompStmt(new NewStmt("a", new VarExp("v")),
                                    new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                            new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

    public static IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                    new CompStmt(new WhileStmt(
                            new RelationalExp(RelOP.GT, new VarExp("v"), new ValueExp(new IntValue(0))),
                            new CompStmt(new PrintStmt(new VarExp("v")),
                                    new AssignStmt("v", new ArithExp(ArithOp.SUB,
                                            new VarExp("v"), new ValueExp(new IntValue(1)))))),
                            new PrintStmt(new VarExp("v")))));
}
