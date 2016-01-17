package com.awesheet.models.arguments;

import com.awesheet.enums.FunctionArgumentType;
import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;

public class EvalFunctionArgument extends FunctionArgument {
    protected DataFunction innerFunction;
    protected boolean valid;

    public EvalFunctionArgument(String value) {
        super(FunctionArgumentType.EVAL_FUNCTION_ARGUMENT_TYPE, value);
        valid = false;
    }

    public void setInnerFunction(DataFunction function) {
        innerFunction = function;
        valid = innerFunction.parse();
    }

    @Override
    public String getValue() {
        return innerFunction != null ? innerFunction.getDisplayValue() : "";
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}