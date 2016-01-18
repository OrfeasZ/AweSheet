package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class ShowPopupAction<T> extends UIAction {
    protected T data;
    protected int popup;

    public ShowPopupAction(int type, T data) {
        super(UIActionType.SHOW_POPUP);

        popup = type;
        this.data = data;
    }
}
