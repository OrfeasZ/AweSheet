package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbsFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        AbsFunction function = new AbsFunction();
        function.addArgument(new ValueFunctionArgument("-10"));

        assertTrue(function.parse());

        assertEquals(10.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        AbsFunction function = new AbsFunction();
        function.addArgument(new ValueFunctionArgument("10"));

        assertTrue(function.parse());

        assertEquals(10.0, function.getInternalValue(), Math.ulp(1.0));
    }
}