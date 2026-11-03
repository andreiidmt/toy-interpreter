package interpreter.model.expression;

import interpreter.exceptions.ExpressionException;
import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static interpreter.model.expression.RelOP.LT;
import static interpreter.model.expression.RelOP.LE;
import static interpreter.model.expression.RelOP.GT;
import static interpreter.model.expression.RelOP.GE;
import static interpreter.model.expression.RelOP.EQ;
import static interpreter.model.expression.RelOP.NE;


import static org.junit.jupiter.api.Assertions.*;

class RelationalExpTest {
    private IDictionary<String, IValue> symTable;

    @BeforeEach
    public void setUp(){
        symTable = new MyDictionary<>();
    }
    @Test
    public void LTf() throws MyException {
        IExp e = new RelationalExp(LT, new ValueExp(new IntValue(8)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }
    @Test
    public void LTt() throws MyException {
        IExp e = new RelationalExp(LT, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }
    @Test
    public void GTf() throws MyException {
        IExp e = new RelationalExp(GT, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }
    @Test
    public void GTt() throws MyException {
        IExp e = new RelationalExp(GT, new ValueExp(new IntValue(8)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }@Test
    public void LEf() throws MyException {
        IExp e = new RelationalExp(LE, new ValueExp(new IntValue(8)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }@Test
    public void LEt() throws MyException {
        IExp e = new RelationalExp(LE, new ValueExp(new IntValue(8)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }@Test
    public void GEf() throws MyException {
        IExp e = new RelationalExp(GE, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }@Test
    public void GEt() throws MyException {
        IExp e = new RelationalExp(GE, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }@Test
    public void EQf() throws MyException {
        IExp e = new RelationalExp(EQ, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }
    @Test
    public void EQt() throws MyException {
        IExp e = new RelationalExp(EQ, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }@Test
    public void NEf() throws MyException {
        IExp e = new RelationalExp(NE, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(7)));
        assertEquals(new BoolValue(false), e.eval(symTable));
    }@Test
    public void NEt() throws MyException {
        IExp e = new RelationalExp(NE, new ValueExp(new IntValue(7)), new ValueExp(new IntValue(8)));
        assertEquals(new BoolValue(true), e.eval(symTable));
    }

    @Test
    public void notIntegerFirst() throws MyException{
        IExp e = new RelationalExp(NE, new ValueExp(new BoolValue(true)), new ValueExp(new IntValue(8)));
        assertThrows(ExpressionException.class, ()->e.eval(symTable));
    }

    @Test
    public void notIntegerSecond() throws MyException{
        IExp e = new RelationalExp(NE, new ValueExp(new IntValue(8)), new ValueExp(new BoolValue(true)));
        assertThrows(ExpressionException.class, ()->e.eval(symTable));
    }




}