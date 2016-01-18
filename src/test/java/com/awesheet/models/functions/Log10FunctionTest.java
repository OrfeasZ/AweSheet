package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Log10FunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        Log10Function function = new Log10Function();
        function.addArgument(new ValueFunctionArgument("1"));

        assertTrue(function.parse());

        assertEquals(0.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        Log10Function function = new Log10Function();
        function.addArgument(new ValueFunctionArgument("10"));

        assertTrue(function.parse());

        assertEquals(1.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue03() throws Exception {
        Log10Function function = new Log10Function();
        function.addArgument(new ValueFunctionArgument("1000"));

        assertTrue(function.parse());

        assertEquals(3.0, function.getInternalValue(), Math.ulp(1.0));
    }
}