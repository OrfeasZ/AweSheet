package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class MedianFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        MedianFunction function = new MedianFunction();
        function.addArgument(new ValueFunctionArgument("5"));
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("2"));
        function.addArgument(new ValueFunctionArgument("4"));
        function.addArgument(new ValueFunctionArgument("3"));

        assertTrue(function.parse());

        assertEquals(3.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        MedianFunction function = new MedianFunction();
        function.addArgument(new ValueFunctionArgument("8"));
        function.addArgument(new ValueFunctionArgument("1"));
        function.addArgument(new ValueFunctionArgument("-5"));
        function.addArgument(new ValueFunctionArgument("7"));
        function.addArgument(new ValueFunctionArgument("3"));
        function.addArgument(new ValueFunctionArgument("6"));

        assertTrue(function.parse());

        assertEquals(4.5, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue03() throws Exception {
        MedianFunction function = new MedianFunction();
        function.addArgument(new ValueFunctionArgument("10"));

        assertTrue(function.parse());

        assertEquals(10.0, function.getInternalValue(), Math.ulp(1.0));
    }
}