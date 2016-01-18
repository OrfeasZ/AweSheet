package com.awesheet.actions;

import com.awesheet.enums.UIActionType;

public class SetSelectedCellsAction extends UIAction {
    protected int sheet;
    protected int cells[][];

    public SetSelectedCellsAction(int sheet, int cells[][]) {
        super(UIActionType.SET_SELECTED_CELLS);

        this.sheet = sheet;
        this.cells = cells;
    }
}
