package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        MinFunction function = new MinFunction();
        function.addArgument(new ValueFunctionArgument("5"));
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("4"));
        function.addArgument(new ValueFunctionArgument("3"));

        assertTrue(function.parse());

        assertEquals(1.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        MinFunction function = new MinFunction();
        function.addArgument(new ValueFunctionArgument("1000"));

        assertTrue(function.parse());

        assertEquals(1000.0, function.getInternalValue(), Math.ulp(1.0));
    }
}