package com.awesheet.models;

import com.awesheet.actions.RemoveCellAction;
import com.awesheet.actions.SetCellAction;
import com.awesheet.actions.SetMaxColumnAction;
import com.awesheet.actions.SetMaxRowAction;
import com.awesheet.enums.UIMessageType;
import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.IUIBindable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.SetCellValueMessage;
import com.awesheet.messages.UIMessage;
import com.awesheet.models.cells.FunctionCell;
import com.awesheet.models.cells.ValueCell;
import com.awesheet.ui.UICell;
import com.awesheet.ui.UIModel;
import com.awesheet.ui.UISheet;

import java.util.Collection;
import java.util.HashMap;

public class Sheet implements IUIBindable, IMessageListener, IDestructible {
    protected String name;
    protected HashMap<String, Cell> cells;
    protected int id;
    protected int maxColumn;
    protected int maxRow;
    protected Workbook workbook;

    public Sheet(Workbook workbook, String name) {
        this.workbook = workbook;
        this.name = name;
        cells = new HashMap<String, Cell>();
        id = 0;
        maxColumn = 0;
        maxRow = 0;

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    public Sheet(String name) {
        this.name = name;
        cells = new HashMap<String, Cell>();
        id = 0;
        maxColumn = 0;
        maxRow = 0;

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    @Override
    public void destroy() {
        cells.clear();

        // Deregister ourselves from the message manager.
        UIMessageManager.getInstance().deregisterListener(this);
    }

    public String getName() {
        return name;
    }

    public Cell getCell(int x, int y) {
        if (!cells.containsKey(x + "x" + y)) {
            return null;
        }

        return cells.get(x + "x" + y);
    }

    public void setCellValue(int x, int y, String value) {
        // Are we trying to remove this cell?
        if (value == null || value.length() == 0) {
            cells.remove(x + "x" + y);
            UIMessageManager.getInstance().dispatchAction(new RemoveCellAction(id, x, y));

            // Recalculate maxRow and maxColumn.
            if (cells.size() == 0) {
                maxColumn = 0;
                maxRow = 0;
            } else {
                Cell firstCell = cells.values().iterator().next();

                maxColumn = firstCell.x + 1;
                maxRow = firstCell.y + 1;

                for (Cell cell : cells.values()) {
                    if (cell.x + 1 > maxColumn) {
                        maxColumn = cell.x + 1;
                    }

                    if (cell.y + 1 > maxRow) {
                        maxRow = cell.y + 1;
                    }
                }
            }

            // Notify UI of new values.
            UIMessageManager.getInstance().dispatchAction(new SetMaxColumnAction(id, maxColumn));
            UIMessageManager.getInstance().dispatchAction(new SetMaxRowAction(id, maxRow));

            return;
        }

        // Do we already have this cell?
        if (cells.containsKey(x + "x" + y)) {
            cells.get(x + "x" + y).destroy();
        }

        // Create our new cell to replace the old one (if it existed).
        Cell cell;

        // All cells with values starting with "=" are function cells.
        if (value.startsWith("=")) {
            cell = new FunctionCell(x, y, value, this);
        } else {
            cell = new ValueCell(x, y, value, this);
        }

        // Check if we need to grow the grid.
        if (x + 1 > maxColumn) {
            maxColumn = x + 1;
            UIMessageManager.getInstance().dispatchAction(new SetMaxColumnAction(id, maxColumn));
        }

        if (y + 1 > maxRow) {
            maxRow = y + 1;
            UIMessageManager.getInstance().dispatchAction(new SetMaxRowAction(id, maxRow));
        }

        cells.put(x + "x" + y, cell);

        // Notify the UI of any changes.
        UIMessageManager.getInstance().dispatchAction(new SetCellAction((UICell) cell.bind()));
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public int getMaxColumn() {
        return maxColumn;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public Collection<Cell> getCells() {
        return cells.values();
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public UIModel bind() {
        UISheet bindable = new UISheet(name, id, maxColumn, maxRow);

        for (Cell cell : cells.values()) {
            bindable.addCell((UICell) cell.bind());
        }

        return bindable;
    }

    @Override
    public void onMessage(UIMessage message) {
        switch (message.getType()) {
            case UIMessageType.SET_CELL_VALUE: {
                SetCellValueMessage uiMessage = (SetCellValueMessage) message;

                // We only care about messages directed to us.
                if (uiMessage.getSheet() == id) {
                    setCellValue(uiMessage.cellX(), uiMessage.cellY(), uiMessage.getValue());
                }

                break;
            }
        }
    }
}
