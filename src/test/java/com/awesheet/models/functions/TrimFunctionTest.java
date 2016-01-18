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