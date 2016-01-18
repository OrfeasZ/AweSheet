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

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;

public class AbsFunction extends DataFunction<Double> {
    public static String getName() {
        return "abs";
    }

    public static String getDescription() {
        return "Calculates the absolute value of the specified number.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "number" };
    }

    public AbsFunction(){
        super(FunctionType.ABS_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 1) {
            return false;
        }

        double parsedValue;

        try {
            parsedValue = Double.parseDouble(arguments.get(0).getValue().trim());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = Math.abs(parsedValue);
        displayValue = Double.toString(internalValue);

        return true;
    }
}
