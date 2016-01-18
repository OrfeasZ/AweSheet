package com.awesheet.models.functions;

import com.awesheet.enums.FunctionType;
import com.awesheet.models.DataFunction;
import com.awesheet.util.Utils;

public class XorFunction extends DataFunction<Boolean> {
    public static String getName() {
        return "xor";
    }

    public static String getDescription() {
        return "Calculates the logical XOR of two values.";
    }

    public static String[] getArgumentNames() {
        return new String[] { "logical1", "logical2" };
    }

    public XorFunction(){
        super(FunctionType.XOR_FUNCTION_TYPE);
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

        internalValue = (parsedValue1 ^ parsedValue2);
        displayValue = internalValue ? "true" : "false";

        return true;
    }
}
