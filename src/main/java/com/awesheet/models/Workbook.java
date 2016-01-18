package com.awesheet.models;

import com.awesheet.actions.*;
import com.awesheet.enums.UIMessageType;
import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.ISerializable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.DeleteSheetMessage;
import com.awesheet.messages.SelectSheetMessage;
import com.awesheet.messages.UIMessage;
import com.awesheet.ui.UISheet;
import com.awesheet.util.BinaryReader;
import com.awesheet.util.BinaryWriter;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Workbook implements ISerializable, IMessageListener, IDestructible {
    private static final int HEADER_MAGIC = 0x57455741;

    protected String path;
    protected HashMap<Integer, Sheet> sheets;
    protected boolean valid;
    protected int newSheetID;
    protected int selectedSheet;

    public Workbook(byte data[], String path) {
        this.path = path;
        valid = false;
        newSheetID = 0;
        selectedSheet = 0;
        sheets = new HashMap<Integer, Sheet>();

        if (deserialize(data))
        {
            // Register ourselves to the message manager.
            UIMessageManager.getInstance().registerListener(this);

            valid = true;
        }
    }

    public Workbook() {
        path = null;
        valid = true;
        newSheetID = 0;
        selectedSheet = 0;
        sheets = new HashMap<Integer, Sheet>();

        // Create an empty sheet.
        addSheet(new Sheet(this, "Sheet 1"));

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    @Override
    public void destroy() {
        sheets.clear();

        // Deregister ourselves from the message manager.
        UIMessageManager.getInstance().deregisterListener(this);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HashMap<Integer, Sheet> getSheets() {
        return sheets;
    }

    public void addSheet(Sheet sheet) {
        // Set ID, increment, and add to list.
        sheet.setID(newSheetID++);
        sheets.put(sheet.getID(), sheet);

        // Notify UI of changes.
        UIMessageManager.getInstance().dispatchAction(new SetSheetAction((UISheet) sheet.bind()));
    }

    public Sheet getSheet(int id) {
        if (!sheets.containsKey(id)) {
            return null;
        }

        return sheets.get(id);
    }

    public void removeSheet(int id) {
        if (!sheets.containsKey(id)) {
            return;
        }

        // Destroy the sheet.
        sheets.get(id).destroy();

        // Remove the sheet from our list.
        sheets.remove(id);

        // Notify the UI of changes.
        UIMessageManager.getInstance().dispatchAction(new RemoveSheetAction(id));
    }

    public void replaceSheet(int id, Sheet newSheet) {
        String sheetName = newSheet.getName();

        // Destroy the sheet if it already exists.
        if (sheets.containsKey(id)) {
            sheetName = sheets.get(id).getName();
            sheets.get(id).destroy();
        }

        // Set the ID of the new sheet and add to list.
        newSheet.setID(id);
        newSheet.setName(sheetName);

        sheets.put(newSheet.getID(), newSheet);

        // Notify UI of changes.
        UIMessageManager.getInstance().dispatchAction(new SetSheetAction((UISheet) newSheet.bind()));
    }

    public void selectSheet(int id) {
        if (!sheets.containsKey(id)) {
            return;
        }

        // Set selected sheet.
        selectedSheet = id;

        // Notify UI of changes.
        UIMessageManager.getInstance().dispatchAction(new SetActiveSheetAction(id));
    }

    public Sheet getSelectedSheet() {
        return getSheet(selectedSheet);
    }

    public boolean isValid() {
        return valid;
    }

    public void addSheets() {
        UIMessageManager.getInstance().dispatchAction(new ClearSheetsAction());

        for (Sheet sheet : sheets.values()) {
            UIMessageManager.getInstance().dispatchAction(new SetSheetAction((UISheet) sheet.bind()));

            int[][] cells = new int[sheet.getSelectedCells().size()][];

            int i = 0;
            for (Point cell : sheet.getSelectedCells()) {
                cells[i++] = new int[] { cell.x, cell.y };
            }

            UIMessageManager.getInstance().dispatchAction(new SetSelectedCellsAction(sheet.getID(), cells));
        }
    }

    @Override
    public byte[] serialize() {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            BinaryWriter writer = new BinaryWriter(stream);

            // Serialize our data.
            writer.write(HEADER_MAGIC);
            writer.write(sheets.size());
            writer.write(newSheetID);
            writer.write(selectedSheet);

            for (Sheet sheet : sheets.values()) {
                byte sheetData[] = sheet.serialize();

                // Sheet failed to serialize; cancel.
                if (sheetData == null) {
                    return null;
                }

                writer.write(sheetData.length);
                writer.write(sheetData);
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
    public boolean deserialize(byte data[]) {
        try {
            BinaryReader reader = new BinaryReader(data);

            int headerMagic = reader.readInt();

            if (headerMagic != HEADER_MAGIC) {
                return false;
            }

            int sheetCount = reader.readInt();
            newSheetID = reader.readInt();
            selectedSheet = reader.readInt();

            for (int i = 0; i < sheetCount; ++i) {
                int dataLength = reader.readInt();
                Sheet readSheet = new Sheet(reader.readBytes(dataLength));

                // Sheet failed to deserialize.
                if (!readSheet.getValid()) {
                    return false;
                }

                sheets.put(readSheet.getID(), readSheet);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onMessage(UIMessage message) {
        switch (message.getType()) {
            case UIMessageType.CREATE_SHEET: {
                addSheet(new Sheet(this, "Sheet " + (newSheetID + 1)));
                break;
            }

            case UIMessageType.SELECT_SHEET: {
                SelectSheetMessage uiMessage = (SelectSheetMessage) message;
                selectSheet(uiMessage.getSheet());
                break;
            }

            case UIMessageType.DELETE_SHEET: {
                DeleteSheetMessage uiMessage = (DeleteSheetMessage) message;
                removeSheet(uiMessage.getSheet());
                break;
            }
        }
    }
}