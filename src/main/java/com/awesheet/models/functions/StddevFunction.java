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

import java.util.List;

public class StddevFunction extends DataFunction<Double> {
    public static String getName() {
        return "stddev";
    }

    public static String getDescription() {
        return "Calculates the standard deviation for the specified set of numbers.";
    }

    public static String[] getArgumentNames() {
        return new String[] {};
    }

    public StddevFunction(){
        super(FunctionType.STDDEV_FUNCTION_TYPE);
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

        double sum = 0.0;

        for (Double value : parsedValues) {
            sum += value;
        }

        double mean = sum / arguments.size();

        double stddev = 0.0;

        for (Double value : parsedValues) {
            stddev += Math.pow(value - mean, 2);
        }

        stddev /= (double) (arguments.size() - 1);
        stddev = Math.sqrt(stddev);

        internalValue = stddev;
        displayValue = Double.toString(internalValue);

        return true;
    }
}
