package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        PowFunction function = new PowFunction();
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("2"));

        assertTrue(function.parse());

        assertEquals(4.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        PowFunction function = new PowFunction();
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("0"));

        assertTrue(function.parse());

        assertEquals(1.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue03() throws Exception {
        PowFunction function = new PowFunction();
        function.addArgument(new ValueFunctionArgument("4"));
        function.addArgument(new ValueFunctionArgument("-1"));

        assertTrue(function.parse());

        assertEquals(0.25, function.getInternalValue(), Math.ulp(1.0));
    }
}