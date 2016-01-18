package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        RemoveFunction function = new RemoveFunction();
        function.addArgument(new ValueFunctionArgument("helloworld"));
        function.addArgument(new ValueFunctionArgument("world"));

        assertTrue(function.parse());

        assertEquals("hello", function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        RemoveFunction function = new RemoveFunction();
        function.addArgument(new ValueFunctionArgument("helloworld"));
        function.addArgument(new ValueFunctionArgument("l"));

        assertTrue(function.parse());

        assertEquals("heoword", function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        RemoveFunction function = new RemoveFunction();
        function.addArgument(new ValueFunctionArgument("helloworld"));
        function.addArgument(new ValueFunctionArgument("s"));

        assertTrue(function.parse());

        assertEquals("helloworld", function.getInternalValue());
    }
}