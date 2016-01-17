package com.awesheet.models;

import com.awesheet.actions.RemoveSheetAction;
import com.awesheet.actions.SetActiveSheetAction;
import com.awesheet.actions.SetSheetAction;
import com.awesheet.enums.UIMessageType;
import com.awesheet.interfaces.IDestructible;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.ISerializable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.DeleteSheetMessage;
import com.awesheet.messages.SelectSheetMessage;
import com.awesheet.messages.UIMessage;
import com.awesheet.ui.UISheet;

import java.util.HashMap;

public class Workbook implements ISerializable, IMessageListener, IDestructible {
    protected String path;
    protected HashMap<Integer, Sheet> sheets;
    protected boolean valid;
    protected int newSheetID;
    protected int selectedSheet;

    public Workbook(byte[] data, String path) {
        this.path = path;
        valid = false;
        newSheetID = 0;
        selectedSheet = 0;
        sheets = new HashMap<Integer, Sheet>();

        // Register ourselves to the message manager.
        UIMessageManager.getInstance().registerListener(this);

        if (deserialize(data))
            valid = true;
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
        // Destroy the sheet if it already exists.
        if (sheets.containsKey(id)) {
            sheets.get(id).destroy();
        }

        // Set the ID of the new sheet and add to list.
        newSheet.setID(id);
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

    public boolean isValid() {
        return valid;
    }

    @Override
    public byte[] serialize() {
        return new byte[0];
    }

    @Override
    public boolean deserialize(byte[] data) {
        return false;
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