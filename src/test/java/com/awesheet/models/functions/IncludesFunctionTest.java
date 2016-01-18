package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncludesFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        IncludesFunction function = new IncludesFunction();
        function.addArgument(new ValueFunctionArgument("hello"));
        function.addArgument(new ValueFunctionArgument("el"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        IncludesFunction function = new IncludesFunction();
        function.addArgument(new ValueFunctionArgument("w0rld"));
        function.addArgument(new ValueFunctionArgument("john"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }
}