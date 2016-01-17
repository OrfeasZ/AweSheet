package com.awesheet.models.functions;

import com.awesheet.models.FunctionArgument;
import com.awesheet.models.DataFunction;
import com.awesheet.enums.FunctionType;

public class Log10Function extends DataFunction {
    Log10Function(FunctionArgument[] arguments){
        super(FunctionType.LOG_10_FUNCTION_TYPE, arguments);
    }

    @Override
    public String getDisplayValue() {
        return null;
    }

    @Override
    public String getValue() {return null;}

    @Override
    public boolean isValid() {
        return false;
    }
}
