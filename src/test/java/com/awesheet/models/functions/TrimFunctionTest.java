package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrimFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        TrimFunction function = new TrimFunction();
        function.addArgument(new ValueFunctionArgument(" hello world "));

        assertTrue(function.parse());

        assertEquals("hello world", function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        TrimFunction function = new TrimFunction();
        function.addArgument(new ValueFunctionArgument("hello world "));

        assertTrue(function.parse());

        assertEquals("hello world", function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        TrimFunction function = new TrimFunction();
        function.addArgument(new ValueFunctionArgument(" hello world"));

        assertTrue(function.parse());

        assertEquals("hello world", function.getInternalValue());
    }

    @Test
    public void testResultValue04() throws Exception {
        TrimFunction function = new TrimFunction();
        function.addArgument(new ValueFunctionArgument("hello world"));

        assertTrue(function.parse());

        assertEquals("hello world", function.getInternalValue());
    }
}