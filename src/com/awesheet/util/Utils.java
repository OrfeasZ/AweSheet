package com.awesheet.util;

import com.awesheet.models.FunctionArgument;

import java.awt.*;
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

    public static Point parseCell(String value) {
        value = value.toUpperCase().trim();
        int rowIndex = 0;
        int column = 0;
        int index = 1;

        for (int i = value.length() - 1; i >= 0; --i) {
            char c = value.charAt(i);

            if (Character.isDigit(c)) {
                if (rowIndex != 0 && rowIndex - i != 1) {
                    return null;
                }

                rowIndex = i;
                continue;
            }

            if (Character.isLetter(c)) {
                column += (c - 65 + 1) * index;
                index *= 26;
            }
        }

        if (rowIndex <= 0 || column <= 0) {
            return null;
        }

        int row;

        try {
            row = Integer.parseInt(value.substring(rowIndex));
        } catch (NumberFormatException e) {
            return null;
        }

        if (row <= 0) {
            return null;
        }

        return new Point(column - 1, row - 1);
    }
}
