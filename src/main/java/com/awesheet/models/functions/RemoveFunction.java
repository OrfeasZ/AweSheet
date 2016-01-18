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

public class RemoveFunction extends DataFunction<String> {
    public static String getName() {
        return "remove";
    }

    public static String getDescription() {
        return "Returns the first argument with all the instances of the second argument removed.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "argument1", "argument2" };
    }

    public RemoveFunction(){
        super(FunctionType.REMOVE_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() != 2) {
            return false;
        }

        String argument1Value = arguments.get(0).getValue();
        String argument2Value = arguments.get(1).getValue();

        if (argument1Value.length() == 0 || argument2Value.length() == 0) {
            return false;
        }

        internalValue = argument1Value.replace(argument2Value, "");
        displayValue = internalValue;

        return true;
    }
}
