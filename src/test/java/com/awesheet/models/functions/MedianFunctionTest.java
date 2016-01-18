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