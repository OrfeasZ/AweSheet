package com.awesheet.util;

import com.awesheet.models.FunctionArgument;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean parseBool(String value) {
        value = value.toLowerCase().trim();

        if (value.equals("0") || value.equals("false") || value.equals("no") || value.equals("n")) {
            return false;
        } else if (value.equals("1") || value.equals("true") || value.equals("yes") || value.equals("y")) {
            return true;
        }

        throw new NumberFormatException();
    }

    public static List<Double> parseDoubleArguments(List<FunctionArgument> arguments) {
        ArrayList<Double> parsedValues = new ArrayList<Double>();

        for (FunctionArgument argument : arguments) {
            try {
                parsedValues.add(Double.parseDouble(argument.getValue().trim()));
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return parsedValues;
    }
}
