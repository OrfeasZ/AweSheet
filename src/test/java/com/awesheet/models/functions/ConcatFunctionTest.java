package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConcatFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        ConcatFunction function = new ConcatFunction();
        function.addArgument(new ValueFunctionArgument("hello"));
        function.addArgument(new ValueFunctionArgument("world"));

        assertTrue(function.parse());

        assertEquals("helloworld", function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        ConcatFunction function = new ConcatFunction();
        function.addArgument(new ValueFunctionArgument("hello "));
        function.addArgument(new ValueFunctionArgument("world"));

        assertTrue(function.parse());

        assertEquals("hello world", function.getInternalValue());
    }
}