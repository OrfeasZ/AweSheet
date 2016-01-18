package com.awesheet.models;

import com.awesheet.MainFrame;
import com.awesheet.actions.*;
import com.awesheet.enums.UIMessageType;
import com.awesheet.handlers.MenuHandler;
import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.ISerializable;
import com.awesheet.interfaces.IUIBindable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.SetCellValueMessage;
import com.awesheet.messages.SetSelectedCellsMessage;
import com.awesheet.messages.UIMessage;
import com.awesheet.models.cells.FunctionCell;
import com.awesheet.models.cells.ValueCell;
import com.awesheet.ui.UICell;
import com.awesheet.ui.UIModel;
import com.awesheet.ui.UISheet;
import com.awesheet.util.BinaryReader;
import com.awesheet.util.BinaryWriter;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.List;

public class Sheet implements IUIBindable, IMessageListener, IDestructible, ISerializable {
    protected String name;
    protected HashMap<String, Cell> cells;
    protected HashSet<Point> selectedCells;
    protected int id;
    protected int maxColumn;
    protected int maxRow;
    protected Workbook workbook;
    protected boolean valid;

    public Sheet(Workbook workbook, String name) {
        this.workbook = workbook;
        this.name = name;
        cells = new HashMap<String, Cell>();
        selectedCells = new HashSet<Point>();
        id = 0;
        maxColumn = 0;
        maxRow = 0;
        valid = true;

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    public Sheet(String name) {
        this.name = name;
        cells = new HashMap<String, Cell>();
        selectedCells = new HashSet<Point>();
        id = 0;
        maxColumn = 0;
        maxRow = 0;
        valid = true;

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    public Sheet(byte[] data) {
        valid = false;
        cells = new HashMap<String, Cell>();
        selectedCells = new HashSet<Point>();

        if (deserialize(data))
        {
            // Register ourselves to the message manager.
            UIMessageManager.getInstance().registerListener(this);

            valid = true;
        }
    }

    @Override
    public void destroy() {
        cells.clear();

        // Deregister ourselves from the message manager.
        UIMessageManager.getInstance().deregisterListener(this);
    }

    public boolean getValid() {
        return valid;
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

    public Cell getCell(String cell) {
        if (!cells.containsKey(cell)) {
            return null;
        }

        return cells.get(cell);
    }

    public void setCellValue(int x, int y, String value, boolean silent) {
        // Are we trying to remove this cell?
        if (value == null || value.length() == 0) {
            cells.remove(x + "x" + y);

            if (!silent) {
                UIMessageManager.getInstance().dispatchAction(new RemoveCellAction(id, x, y));
            }

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
            if (!silent) {
                UIMessageManager.getInstance().dispatchAction(new SetMaxColumnAction(id, maxColumn));
                UIMessageManager.getInstance().dispatchAction(new SetMaxRowAction(id, maxRow));
            }

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

            if (!silent) {
                UIMessageManager.getInstance().dispatchAction(new SetMaxColumnAction(id, maxColumn));
            }
        }

        if (y + 1 > maxRow) {
            maxRow = y + 1;

            if (!silent) {
                UIMessageManager.getInstance().dispatchAction(new SetMaxRowAction(id, maxRow));
            }
        }

        cells.put(x + "x" + y, cell);

        // Notify the UI of any changes.
        if (!silent) {
            UIMessageManager.getInstance().dispatchAction(new SetCellAction((UICell) cell.bind()));
        }
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

    public HashSet<Point> getSelectedCells() {
        return selectedCells;
    }

    public Cell[] collectSelectedCells() {
        List<Cell> collectedCells = new ArrayList<Cell>();

        for (Point selectedCell : selectedCells) {
            Cell cell = getCell(selectedCell.x, selectedCell.y);

            if (cell != null) {
                collectedCells.add(cell);
            }
        }

        return collectedCells.toArray(new Cell[collectedCells.size()]);
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
                    setCellValue(uiMessage.cellX(), uiMessage.cellY(), uiMessage.getValue(), false);
                }

                break;
            }

            case UIMessageType.SET_SELECTED_CELLS: {
                SetSelectedCellsMessage uiMessage = (SetSelectedCellsMessage) message;

                // We only care about messages directed to us.
                if (uiMessage.getSheet() != id) {
                    break;
                }

                // Update our selected cells.
                selectedCells.clear();

                for (int cell[] : uiMessage.getCells()) {
                    selectedCells.add(new Point(cell[0], cell[1]));
                }

                // Update menu bar item states.
                MainFrame.getInstance().getMenuHandler().getChartBarItem().setEnabled(selectedCells.size() > 4);
                MainFrame.getInstance().getMenuHandler().getChartLineItem().setEnabled(selectedCells.size() > 4);
                MainFrame.getInstance().getMenuHandler().getEditCopyItem().setEnabled(selectedCells.size() == 1);
                MainFrame.getInstance().getMenuHandler().getEditCutItem().setEnabled(selectedCells.size() == 1);
                MainFrame.getInstance().getMenuHandler().getEditPasteItem().setEnabled(selectedCells.size() == 1);

                break;
            }
        }
    }

    @Override
    public byte[] serialize() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            BinaryWriter writer = new BinaryWriter(stream);

            // Serialize our data.
            writer.write(id);
            writer.write(maxColumn);
            writer.write(maxRow);
            writer.write(name.length());
            writer.write(cells.size());
            writer.write(selectedCells.size());

            // Write name.
            writer.write(name.getBytes());

            // Write cells.
            for (Cell cell : cells.values()) {
                writer.write(cell.x);
                writer.write(cell.y);
                writer.write(cell.value.length());
                writer.write(cell.value.getBytes());
            }

            // Write selected cells.
            for (Point cell : selectedCells) {
                writer.write(cell.x);
                writer.write(cell.y);
            }

            // Get final data.
            writer.flush();
            byte serializedData[] = writer.toByteArray();
            stream.close();

            return serializedData;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deserialize(byte[] data) {
        try {
            BinaryReader reader = new BinaryReader(data);

            id = reader.readInt();
            maxColumn = reader.readInt();
            maxRow = reader.readInt();

            int nameLength = reader.readInt();
            int cellCount = reader.readInt();
            int selectedCellCount = reader.readInt();

            name = new String(reader.readBytes(nameLength));

            for (int i = 0; i < cellCount; ++i) {
                int x = reader.readInt();
                int y = reader.readInt();
                int valueLength = reader.readInt();
                String value = new String(reader.readBytes(valueLength));
                setCellValue(x, y, value, true);
            }

            for (int i = 0; i < selectedCellCount; ++i) {
                int x = reader.readInt();
                int y = reader.readInt();
                selectedCells.add(new Point(x, y));
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
