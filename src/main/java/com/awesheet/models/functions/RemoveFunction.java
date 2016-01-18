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
