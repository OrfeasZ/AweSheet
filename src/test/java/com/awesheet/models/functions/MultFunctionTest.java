package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        MultFunction function = new MultFunction();
        function.addArgument(new ValueFunctionArgument("5"));
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("4"));
        function.addArgument(new ValueFunctionArgument("3"));

        assertTrue(function.parse());

        assertEquals(120.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        MultFunction function = new MultFunction();
        function.addArgument(new ValueFunctionArgument("-10"));

        assertTrue(function.parse());

        assertEquals(-10.0, function.getInternalValue(), Math.ulp(1.0));
    }
}