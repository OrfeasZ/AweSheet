package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class AndFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        AndFunction function = new AndFunction();
        function.addArgument(new ValueFunctionArgument("0"));
        function.addArgument(new ValueFunctionArgument("true"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        AndFunction function = new AndFunction();
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("yes"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        AndFunction function = new AndFunction();
        function.addArgument(new ValueFunctionArgument("y"));
        function.addArgument(new ValueFunctionArgument("no"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }
}