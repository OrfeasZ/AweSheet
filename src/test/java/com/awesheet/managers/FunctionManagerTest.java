/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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