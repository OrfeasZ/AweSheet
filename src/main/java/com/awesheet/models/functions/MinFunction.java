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

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;

public class MinFunction extends DataFunction<Double> {
    public static String getName() {
        return "min";
    }

    public static String getDescription() {
        return "Returns the minimum value from the specified set of numbers.";
    }

    public static String[] getArgumentNames() {
        return new String[] {};
    }

    public MinFunction(){
        super(FunctionType.MIN_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        double min = 0.0;
        boolean hasMin = false;

        for (FunctionArgument argument : arguments) {
            double parsedValue;

            try {
                parsedValue = Double.parseDouble(argument.getValue().trim());
            } catch (NumberFormatException e) {
                return false;
            }

            if (!hasMin) {
                hasMin = true;
                min = parsedValue;
            }

            if (parsedValue < min) {
                min = parsedValue;
            }
        }

        internalValue = min;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
