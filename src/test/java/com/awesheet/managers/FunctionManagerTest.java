package com.awesheet.managers;

import org.junit.Test;

import static com.awesheet.managers.FunctionManager.getInstance;
import static org.junit.Assert.*;

public class FunctionManagerTest {

    @Test
    public void testParseFunction() throws Exception {
        assertEquals(14.0, (Double) getInstance().parseFunction("=abs(-14)", null).getInternalValue(), Math.ulp(1.0));
        assertEquals(14.0, (Double) getInstance().parseFunction("=abs(=sum(12.0, -26))", null).getInternalValue(), Math.ulp(1.0));
        assertEquals(1012.0, (Double) getInstance().parseFunction("=abs(=sum(12.0, =pow(-4, 5)))", null).getInternalValue(), Math.ulp(1.0));
        assertEquals("hello world", getInstance().parseFunction("=concat(\"hello\", \" world\")", null).getInternalValue());
        assertEquals("hello world", getInstance().parseFunction("=concat(=trim(\"  hello  \"), \" world\")", null).getInternalValue());
    }
}