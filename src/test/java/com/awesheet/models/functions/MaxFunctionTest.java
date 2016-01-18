package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaxFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        MaxFunction function = new MaxFunction();
        function.addArgument(new ValueFunctionArgument("-100"));
        function.addArgument(new ValueFunctionArgument("0"));
        function.addArgument(new ValueFunctionArgument("1000"));
        function.addArgument(new ValueFunctionArgument("10"));
        function.addArgument(new ValueFunctionArgument("1"));

        assertTrue(function.parse());

        assertEquals(1000.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        MaxFunction function = new MaxFunction();
        function.addArgument(new ValueFunctionArgument("-300"));

        assertTrue(function.parse());

        assertEquals(-300.0, function.getInternalValue(), Math.ulp(1.0));
    }
}