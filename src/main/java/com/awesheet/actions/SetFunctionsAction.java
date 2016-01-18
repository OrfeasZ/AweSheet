package com.awesheet.actions;

import com.awesheet.enums.UIActionType;
import com.awesheet.ui.UIFunction;

import java.util.HashMap;

public class SetFunctionsAction extends UIAction {
    protected HashMap<String, UIFunction> functions;

    public SetFunctionsAction(HashMap<String, UIFunction> functions) {
        super(UIActionType.SET_FUNCTIONS);
        this.functions = functions;
    }
}
