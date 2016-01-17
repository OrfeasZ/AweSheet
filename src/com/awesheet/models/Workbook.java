package com.awesheet.models;

import com.awesheet.enums.UIMessageType;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.interfaces.ISerializable;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.messages.DeleteSheetMessage;
import com.awesheet.messages.SelectSheetMessage;
import com.awesheet.messages.UIMessage;
import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.HashSet;

public class Workbook implements ISerializable, IMessageListener {
    protected String path;
    protected HashSet<Sheet> sheets;

    public Workbook(byte[] data, String path) {
        this.path = path;

        UIMessageManager.getInstance().registerListener(this);

        // TODO: Parse workbook.
    }

    public Workbook() {
        path = null;

        UIMessageManager.getInstance().registerListener(this);
    }

    public String getPath() {
        return path;
    }

    public HashSet<Sheet> getSheets() {
        return sheets;
    }

    public void addSheet(Sheet sheet) {
    }

    public Sheet getSheet(int id) {
        return null;
    }

    public void removeSheet(int id) {

    }

    public void replaceSheet(int id, Sheet newSheet) {

    }

    public boolean isValid() {
        return false;
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
                System.out.println("Creating sheet.");
                // TODO: Create sheet.
                break;
            }

            case UIMessageType.SELECT_SHEET: {
                SelectSheetMessage uiMessage = (SelectSheetMessage) message;

                System.out.println("Selecting sheet " + uiMessage.getSheet());

                break;
            }

            case UIMessageType.DELETE_SHEET: {
                DeleteSheetMessage uiMessage = (DeleteSheetMessage) message;

                System.out.println("Deleting sheet " + uiMessage.getSheet());

                break;
            }
        }
    }
}