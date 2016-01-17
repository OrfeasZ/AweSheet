package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;
import com.awesheet.util.Utils;

import java.util.*;

public class MedianFunction extends DataFunction {
    public static String getName() {
        return "median";
    }

    MedianFunction(){
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

        internalValue = Double.toString(median);

        return true;
    }
}
