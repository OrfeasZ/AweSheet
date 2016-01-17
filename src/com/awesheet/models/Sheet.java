package com.awesheet.models;

import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.IUIBindable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.UIMessage;
import com.awesheet.ui.UICell;
import com.awesheet.ui.UIModel;
import com.awesheet.ui.UISheet;

import java.util.HashMap;

public class Sheet implements IUIBindable, IMessageListener, IDestructible {
    protected String name;
    protected HashMap<String, Cell> cells;
    protected int id;
    protected int maxColumn;
    protected int maxRow;

    public Sheet(String name) {
        this.name = name;
        cells = new HashMap<String, Cell>();
        id = 0;
        maxColumn = 0;
        maxRow = 0;

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

    public void setName(String name) {
        this.name = name;
    }

    public Cell getCell(int x, int y) {
        if (!cells.containsKey(x + "x" + y)) {
            return null;
        }

        return cells.get(x + "x" + y);
    }

    public void setCellValue(int x, int y, String value) {

    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public int getMaxColumnt() {
        return maxColumn;
    }

    public int getMaxRow() {
        return maxRow;
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

    }
}
