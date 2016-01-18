package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        OrFunction function = new OrFunction();
        function.addArgument(new ValueFunctionArgument("no"));
        function.addArgument(new ValueFunctionArgument("0"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        OrFunction function = new OrFunction();
        function.addArgument(new ValueFunctionArgument("false"));
        function.addArgument(new ValueFunctionArgument("n"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        OrFunction function = new OrFunction();
        function.addArgument(new ValueFunctionArgument("y"));
        function.addArgument(new ValueFunctionArgument("true"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue04() throws Exception {
        OrFunction function = new OrFunction();
        function.addArgument(new ValueFunctionArgument("yes"));
        function.addArgument(new ValueFunctionArgument("1"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }
}