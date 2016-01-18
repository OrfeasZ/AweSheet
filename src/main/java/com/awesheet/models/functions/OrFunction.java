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

import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

public class OrFunction extends DataFunction<Boolean> {
    public static String getName() {
        return "or";
    }

    public static String getDescription() {
        return "Calculates the logical OR of two values.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "logical1", "logical2" };
    }

    public OrFunction(){
        super(FunctionType.OR_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        boolean parsedValue1;
        boolean parsedValue2;

        try {
            parsedValue1 = Utils.parseBool(arguments.get(0).getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            parsedValue2 = Utils.parseBool(arguments.get(1).getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        internalValue = (parsedValue1 | parsedValue2);
        displayValue = internalValue ? "true" : "false";

        return true;
    }
}
