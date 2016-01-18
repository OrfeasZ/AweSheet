package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class XorFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        XorFunction function = new XorFunction();
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("0"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        XorFunction function = new XorFunction();
        function.addArgument(new ValueFunctionArgument("false"));
        function.addArgument(new ValueFunctionArgument("yes"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        XorFunction function = new XorFunction();
        function.addArgument(new ValueFunctionArgument("n"));
        function.addArgument(new ValueFunctionArgument("no"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue04() throws Exception {
        XorFunction function = new XorFunction();
        function.addArgument(new ValueFunctionArgument("y"));
        function.addArgument(new ValueFunctionArgument("true"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }
}