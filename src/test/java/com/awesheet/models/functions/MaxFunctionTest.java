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

public class MaxFunctionTest {
    @Test
    public void testResultValue01() throws Exception {
        MaxFunction function = new MaxFunction();
        function.addArgument(new ValueFunctionArgument("-100"));
        function.addArgument(new ValueFunctionArgument("0"));
        function.addArgument(new ValueFunctionArgument("1000"));
        function.addArgument(new ValueFunctionArgument("10"));
        function.addArgument(new ValueFunctionArgument("1"));

        assertTrue(function.parse());

        assertEquals(1000.0, function.getInternalValue(), Math.ulp(1.0));
    }

    @Test
    public void testResultValue02() throws Exception {
        MaxFunction function = new MaxFunction();
        function.addArgument(new ValueFunctionArgument("-300"));

        assertTrue(function.parse());

        assertEquals(-300.0, function.getInternalValue(), Math.ulp(1.0));
    }
}