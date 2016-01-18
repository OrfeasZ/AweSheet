package com.awesheet.models.functions;

import com.awesheet.models.arguments.ValueFunctionArgument;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CosFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        CosFunction function = new CosFunction();
        function.addArgument(new ValueFunctionArgument("0"));

        assertTrue(function.parse());

        assertEquals(1.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        CosFunction function = new CosFunction();
        function.addArgument(new ValueFunctionArgument("90"));

        assertTrue(function.parse());

        assertEquals(0.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue03() throws Exception {
        CosFunction function = new CosFunction();
        function.addArgument(new ValueFunctionArgument("180"));

        assertTrue(function.parse());

        assertEquals(-1.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue04() throws Exception {
        CosFunction function = new CosFunction();
        function.addArgument(new ValueFunctionArgument("360"));

        assertTrue(function.parse());

        assertEquals(1.0, function.getInternalValue(), Math.ulp(1.0));
    }
}