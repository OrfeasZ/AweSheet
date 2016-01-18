package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class StddevFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        StddevFunction function = new StddevFunction();
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("3"));
        function.addArgument(new ValueFunctionArgument("4"));
        function.addArgument(new ValueFunctionArgument("5"));

        assertTrue(function.parse());

        assertEquals(Math.sqrt(5.0 / 2.0), function.getInternalValue(), Math.ulp(1.0));
    }
}