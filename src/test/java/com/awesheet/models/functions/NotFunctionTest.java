package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        NotFunction function = new NotFunction();
        function.addArgument(new ValueFunctionArgument("true"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue02() throws Exception {
        NotFunction function = new NotFunction();
        function.addArgument(new ValueFunctionArgument("false"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }

    @Test
    public void testResultValue03() throws Exception {
        NotFunction function = new NotFunction();
        function.addArgument(new ValueFunctionArgument("y"));

        assertTrue(function.parse());

        assertEquals(false, function.getInternalValue());
    }

    @Test
    public void testResultValue04() throws Exception {
        NotFunction function = new NotFunction();
        function.addArgument(new ValueFunctionArgument("no"));

        assertTrue(function.parse());

        assertEquals(true, function.getInternalValue());
    }
}