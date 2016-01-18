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

import java.util.*;

public class MedianFunction extends DataFunction<Double> {
    public static String getName() {
        return "median";
    }

    public static String getDescription() {
        return "Returns the median value of the specified numbers.";
    }

    public static String[] getArgumentNames() {
        return new String[] {};
    }

    public MedianFunction(){
        super(FunctionType.MEDIAN_FUNCTION_TYPE);
    }

    @Override
    public boolean parse() {
        if (arguments.size() == 0) {
            return false;
        }

        List<Double> parsedValues = Utils.parseDoubleArguments(arguments);

        if (parsedValues == null) {
            return false;
        }

        Collections.sort(parsedValues);

        double median;

        if (parsedValues.size() % 2 == 0) {
            median = (parsedValues.get(parsedValues.size() / 2) + parsedValues.get((parsedValues.size() / 2) - 1)) / 2.0;
        } else {
            median = parsedValues.get(parsedValues.size() / 2);
        }

        internalValue = median;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
