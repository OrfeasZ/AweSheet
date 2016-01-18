package com.awesheet.messages;

import com.awesheet.enums.UIMessageType;

public class SetSelectedCellsMessage extends UIMessage {
    protected int sheet;
    protected int cells[][];

    public SetSelectedCellsMessage() {
        super(UIMessageType.SET_SELECTED_CELLS);
    }

    public int[][] getCells() {
        return cells;
    }

    public int getSheet() {
        return sheet;
    }
}
